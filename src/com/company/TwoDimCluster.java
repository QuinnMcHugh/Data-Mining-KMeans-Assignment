package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quinnmchugh on 3/25/17.
 */
public class TwoDimCluster extends Cluster {
    public void computeNewCentroid(){
        // Create running count sumVector which contains sums of x1 and x2
        List<Double> sumVector = new ArrayList<>();
        sumVector.add(0d);
        sumVector.add(0d);

        for (DataRecord dr : getRecords()){
            List<Double> vector = dr.toVector();
            sumVector.set(0, sumVector.get(0) + vector.get(0));
            sumVector.set(1, sumVector.get(1) + vector.get(1));
        }

        sumVector.set(0, sumVector.get(0) / getRecords().size());
        sumVector.set(1, sumVector.get(1) / getRecords().size());

        TwoDimRecord average = new TwoDimRecord();
        average.x1 = sumVector.get(0);
        average.x2 = sumVector.get(1);

        setCentroid(average);
    }
}
