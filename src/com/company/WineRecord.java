package com.company;

import java.util.ArrayList;
import java.util.List;

public class WineRecord implements DataRecord {
    public int ID;
    public double fx_acidity;
    public double vol_acidity;
    public double citric_acid;
    public double resid_sugar;
    public double chlorides;
    public double free_sulf_d;
    public double tot_sulf_d;
    public double density;
    public double pH;
    public double sulph;
    public double alcohol;
    public double quality;
    public String classDescription;

    public int getID(){ return ID; }
    public String getClassName(){ return classDescription; }

    public String toString(){
        return "{ID: " + ID + ", fx: " + fx_acidity + ", vol: " + vol_acidity +
                ", citric: " + citric_acid + ", resid: " + resid_sugar + ", chlorides: " +
                chlorides + ", free_sulf_d: " + free_sulf_d + ", tot_sulf_d: " + tot_sulf_d +
                ", density: " + density + ", pH: " + pH + ", sulph: " + sulph + ", alcohol: " +
                alcohol + ", quality: " + quality + ", classDescription: " + classDescription + "}";
    }

    public List<Double> toVector(){
        List<Double> vector = new ArrayList<Double>();

        // Fill vector with normalized/computed values based on exploratory
        // analysis findings
        vector.add((fx_acidity - 8.32) / 1.74);
        vector.add((vol_acidity - .53) / .18);
        vector.add((citric_acid - .27) / .27);
        vector.add((resid_sugar - 2.54) / 2.54);
        vector.add((chlorides - .09) / .05);
        vector.add((free_sulf_d - 15.87) / 15.87);
        vector.add((tot_sulf_d - 46.47) / 46.47);
        vector.add((density - .9967) / .0019);
        vector.add((pH - 3.311) / .1544);
        vector.add((sulph - .6581) / .1695);
        vector.add((alcohol - 10.423) / 10.423);

        return vector;
    }

    public void randomPopulate(){
        // Randomly populate with numbers somewhere in the logical range
        fx_acidity = (3 * Math.random() - 1.5) * 1.74 + 8.32;
        vol_acidity = (3 * Math.random() - 1.5) * .18 + .53;
        citric_acid = Math.random() * .9;
        resid_sugar = Math.random() * (5 - 1.5) + 1.5;
        chlorides = (3 * Math.random() - 1.5) * .05 + .09;
        free_sulf_d = Math.random() * (30 - 5) + 5;
        tot_sulf_d = Math.random() * (100 - 15) + 15;
        density = (3 * Math.random() - 1.5) * .0019 + .9967;
        pH = (3 * Math.random() - 1.5) * .1544 + 3.311;
        sulph = (3 * Math.random() - 1.5) * .1695 + .6581;
        alcohol = Math.random() * (13 - 9) + 9;
    }
}
