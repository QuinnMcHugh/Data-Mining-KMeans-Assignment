package com.company;

public class Main {

    public static void main(String[] args) {
        CSVData csvTwoDimEasy = new CSVData("input/TwoDimEasy.csv");
        DataFrame dfTwoDimEasy = new TwoDimDataFrame();

        dfTwoDimEasy.parseFromCSV(csvTwoDimEasy);

        KMeans kmTwoDimEasy = new KMeans(2, dfTwoDimEasy, TwoDimRecord.class);
        kmTwoDimEasy.execute();
    }
}
