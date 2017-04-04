package com.company;

import java.util.*;

public class TwoDimRecord implements DataRecord {
	public int ID;
	public double x1;
	public double x2;
	public String clusterClass;

	public int getID(){ return ID; }
	public String getClassName(){ return clusterClass; }
	
	public String toString(){
		return "{ID: " + ID + ", x1: " + x1 + ", x2: " + x2 +
		", clusterClass: " + clusterClass + "}";
	}
	
	public List<Double> toVector(){
		List<Double> vector = new ArrayList<Double>();

		// vector is simply the two provided numbers
		vector.add(x1);
		vector.add(x2);
		
		return vector;
	}

	public void randomPopulate(){
		// After observing the provided 2 Dim data, I decided the range
		// [0, 2) to be fine for randomly populating
		x1 = Math.random() * 1;
		x2 = Math.random() * 1;
	}
}
