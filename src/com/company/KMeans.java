package com.company;

import java.util.ArrayList;
import java.util.List;

public class KMeans {
    // produce table method
    // randomly generate K centroids

    // private data of clusters
    // private data of dataframe

    private int K;
    private List<Cluster> clusters;
    private DataFrame df;
    private Class recordType;

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

    public void execute(){
        generateRandomClusters();

        // Iterate until K initial centroids become fixed, approx 50 times
        for (int i = 0; i < 50; i++){
            // Iterate through all data points and assign each
            // one to it's closest cluster
            for (int j = 0; j < df.getRecords().size(); j++){
                assignToCluster(df.getRecords().get(j));
            }

            for (int j = 0; j < clusters.size(); j++){
                clusters.get(j).computeNewCentroid();
                clusters.get(j).getRecords().clear();
            }
        }

        System.out.println("Final Cluster : " + clusters.get(0).getCentroid());
        System.out.println("Final Cluster : " + clusters.get(1).getCentroid());
    }
}
