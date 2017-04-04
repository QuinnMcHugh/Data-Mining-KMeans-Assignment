package com.company;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public abstract class Cluster {
    private DataRecord centroid;
    private Set<DataRecord> records;

    public Cluster(){
        records = new HashSet<DataRecord>();
    }

    public void setCentroid(DataRecord center){ this.centroid = center; }

    public Set<DataRecord> getRecords(){ return records; }
    public DataRecord getCentroid(){ return centroid; }

    // This is the one DataRecord implementation dependent record
    public abstract void computeNewCentroid();

    public double computeSSE(){
        double result = 0;

        SimilarityMetric metric = new SimilarityMetric(SimilarityMetric.MetricType.EUCLIDEAN);
        for (DataRecord dr : records){
            result += Math.pow(metric.calculate(centroid.toVector(), dr.toVector()), 2);
        }

        return result;
    }
}
