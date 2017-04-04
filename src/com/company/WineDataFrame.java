package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WineDataFrame implements DataFrame {
    private List<DataRecord> records;

    public WineDataFrame(){
        records = new ArrayList<DataRecord>();
    }

    public void parseFromCSV(CSVData csv){
        List<HashMap<String, String>> csvRecords = csv.getRecords();

        for (int i = 0; i < csvRecords.size(); i++){
            // Create new WineRecord
            WineRecord data = new WineRecord();

            data.ID = Integer.parseInt(csvRecords.get(i).get("ID").trim());
            data.fx_acidity = Double.parseDouble(csvRecords.get(i).get("fx_acidity"));
            data.vol_acidity = Double.parseDouble(csvRecords.get(i).get("vol_acidity"));
            data.citric_acid = Double.parseDouble(csvRecords.get(i).get("citric_acid"));
            data.resid_sugar = Double.parseDouble(csvRecords.get(i).get("resid_sugar"));
            data.chlorides = Double.parseDouble(csvRecords.get(i).get("chlorides"));
            data.free_sulf_d = Double.parseDouble(csvRecords.get(i).get("free_sulf_d"));
            data.tot_sulf_d = Double.parseDouble(csvRecords.get(i).get("tot_sulf_d"));
            data.density = Double.parseDouble(csvRecords.get(i).get("density"));
            data.pH = Double.parseDouble(csvRecords.get(i).get("pH"));
            data.sulph = Double.parseDouble(csvRecords.get(i).get("sulph"));
            data.alcohol = Double.parseDouble(csvRecords.get(i).get("alcohol"));
            data.quality = Double.parseDouble(csvRecords.get(i).get("quality"));
            data.classDescription = csvRecords.get(i).get("classDescription");

            records.add(data);
        }
    }

    public List<DataRecord> getRecords(){ return records; }
}
