package com.company;


import java.util.ArrayList;
import java.util.List;

public class WineCluster extends Cluster {
    public void computeNewCentroid(){
        // Create running count sumVector which contains sums of all the numeric components
        List<Double> sumVector = new ArrayList<>();
        for (int i = 0; i < 11; i++){
            sumVector.add(0d);
        }

        for (DataRecord dr : getRecords()){
            List<Double> vector = dr.toVector();
            for (int i = 0; i < sumVector.size(); i++){
                sumVector.set(i, sumVector.get(i) + vector.get(i));
            }
        }

        for (int i = 0; i < sumVector.size(); i++){
            sumVector.set(i, sumVector.get(i) / getRecords().size());
        }

        WineRecord average = new WineRecord();
        average.fx_acidity = sumVector.get(0);
        average.vol_acidity = sumVector.get(1);
        average.citric_acid = sumVector.get(2);
        average.resid_sugar = sumVector.get(3);
        average.chlorides = sumVector.get(4);
        average.free_sulf_d = sumVector.get(5);
        average.tot_sulf_d = sumVector.get(6);
        average.density = sumVector.get(7);
        average.pH = sumVector.get(8);
        average.sulph = sumVector.get(9);
        average.alcohol = sumVector.get(10);

        setCentroid(average);
    }
}
