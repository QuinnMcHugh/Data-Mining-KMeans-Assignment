package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KMeans {
    private int K;
    private List<Cluster> clusters;
    private DataFrame df;
    private Class recordType;

    public List<Cluster> getClusters(){ return clusters; }

    public KMeans(int k, DataFrame frame, Class recordType){
        this.K = k;
        this.df = frame;
        clusters = new ArrayList<Cluster>();
        this.recordType = recordType;
    }

    private void generateRandomClusters(){
        // Remove any clusters which may have existed beforehand
        clusters.clear();

        for (int i = 0; i < K; i++){
            // Create DataRecord representing new cluster centroid
            DataRecord dr = null;

            // A plethora of errors can occur in the following two lines
            try {
                Class type = Class.forName(recordType.getName());
                dr = (DataRecord)type.newInstance();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            dr.randomPopulate();

            // Use correct implementation of Cluster based on 'recordType'
            Cluster clust = null;
            if (recordType.getSimpleName().equals("TwoDimRecord")){
                clust = new TwoDimCluster();
            }
            else if (recordType.getSimpleName().equals("WineRecord")){
                clust = new WineCluster();
            }

            clust.setCentroid(dr);

            clusters.add(clust);
        }
    }

    private void assignToCluster(DataRecord record){
        double smallestDistance = Double.MAX_VALUE;
        Cluster smallestCluster = null;

        SimilarityMetric metric = new SimilarityMetric(SimilarityMetric.MetricType.EUCLIDEAN);
        for (int i = 0; i < clusters.size(); i++){
            double dist = metric.calculate(record.toVector(), clusters.get(i).getCentroid().toVector());
            if (dist < smallestDistance){
                smallestDistance = dist;
                smallestCluster = clusters.get(i);
            }
        }
        smallestCluster.getRecords().add(record);
    }

    private boolean emptyClustersExist(){
        for (Cluster clust : clusters){
            if (clust.getRecords().size() == 0){
                return true;
            }
        }
        return false;
    }

    private void reassignEmptyClusters(){
        int worstClusterIndex = -1;
        double worstClusterSSE = Double.MIN_VALUE;

        for (int i = 0; i < clusters.size(); i++){
            double score = clusters.get(i).computeSSE();
            if (score > worstClusterSSE){
                worstClusterSSE = score;
                worstClusterIndex = i;
            }
        }

        // Reassign empty clusters to have centroids of points
        // contained in the cluster with highest SSE. Randomly assigned
        for (Cluster clust : clusters){
            if (clust.getRecords().size() == 0){
                int len = clusters.get(worstClusterIndex).getRecords().toArray().length;

                clust.setCentroid((DataRecord) clusters.get(worstClusterIndex).getRecords().toArray()[(int)(Math.random() * len)]);
            }
        }
    }

    private void execute(){
        generateRandomClusters();

        int iterations = 50;
        // Iterate until K initial centroids become fixed, approx 50 times
        for (int i = 0; i < iterations; i++){
            // Iterate through all data points and assign each
            // one to it's closest cluster
            for (int j = 0; j < df.getRecords().size(); j++){
                assignToCluster(df.getRecords().get(j));
            }

            if (emptyClustersExist()){
                i--;
                reassignEmptyClusters();
                continue;
            }

            for (int j = 0; j < clusters.size(); j++){
                clusters.get(j).computeNewCentroid();

                // Cluster records should be cleared in every case
                // except for the last iteration so record placements
                // are retained.
                if (i < iterations - 1){
                    clusters.get(j).getRecords().clear();
                }
            }
        }
    }

    public void produceTable(File output){
        // Process all the data
        execute();

        // The format of this table is
        // ID, Computed Cluster
        String fileText = "ID,Computed Cluster\n";

        // Dump every record into csv format
        int clusterCount = 1;
        for (Cluster c : clusters){
            for (DataRecord dr : c.getRecords()){
                fileText += (dr.getID() + "," + clusterCount + "\n");
            }

            clusterCount++;
        }

        writeToFile(output, fileText);
    }

    public double computeSSB(){
        double ssb = 0d;

        // Compute global m
        Cluster global = clusters.get(0);
        Set<DataRecord> initRecords = new HashSet<DataRecord>();
        initRecords.addAll(global.getRecords());

        for (int i = 1; i < clusters.size(); i++){
            global.getRecords().addAll(clusters.get(i).getRecords());
        }

        global.computeNewCentroid();
        DataRecord m = global.getCentroid();

        // Reset initial cluster to original values
        global.getRecords().clear();
        global.getRecords().addAll(initRecords);
        global.computeNewCentroid();

        SimilarityMetric metric = new SimilarityMetric(SimilarityMetric.MetricType.EUCLIDEAN);
        for (Cluster clust : clusters){
            ssb += clust.getRecords().size() * Math.pow(metric.calculate(m.toVector(), clust.getCentroid().toVector()), 2);
        }

        return ssb;
    }

    private static void writeToFile(File file, String text){
        try {
            PrintWriter p = new PrintWriter(file);
            p.print(text);
            p.close();
        }
        catch (IOException e){
            e.printStackTrace();;
        }
    }
}
