package com.company;

import java.util.*;

public class TwoDimDataFrame implements DataFrame {
	private List<DataRecord> records;
	
	public TwoDimDataFrame(){
		records = new ArrayList<DataRecord>();
	}
	
	public void parseFromCSV(CSVData csv){
		List<HashMap<String, String>> csvRecords = csv.getRecords();

		for (int i = 0; i < csvRecords.size(); i++){
			// Create new IrisRecord
			TwoDimRecord data = new TwoDimRecord();

			data.x1 = Double.parseDouble(csvRecords.get(i).get("X.1").trim());
			data.x2 = Double.parseDouble(csvRecords.get(i).get("X.2").trim());
			data.clusterClass = csvRecords.get(i).get("cluster").trim();
			data.ID = Integer.parseInt(csvRecords.get(i).get("ID").trim());
			
			records.add(data);
		}
	}
	
	public List<DataRecord> getRecords(){ return records; }
}