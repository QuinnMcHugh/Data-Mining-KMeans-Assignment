package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // The number of clusters to be formed
        int k = 3;

        // Load in TwoDimEasy.csv
        CSVData csvTwoDimEasy = new CSVData("input/TwoDimEasy.csv");
        DataFrame dfTwoDimEasy = new TwoDimDataFrame();

        // Parse TwoDimEasy.csv to program-friendly format
        dfTwoDimEasy.parseFromCSV(csvTwoDimEasy);

        // Run KMeans on TwoDimEasy.csv
        KMeans kmTwoDimEasy = new KMeans(k, dfTwoDimEasy, TwoDimRecord.class);
        kmTwoDimEasy.produceTable(new File("out/TwoDimEasy.csv"));

        // --------------------------------------------------------------------

        k = 3;

        // Load in TwoDimHard.csv
        CSVData csvTwoDimHard = new CSVData("input/TwoDimHard.csv");
        DataFrame dfTwoDimHard = new TwoDimDataFrame();

        // Parse TwoDimHard.csv to program-friendly format
        dfTwoDimHard.parseFromCSV(csvTwoDimHard);

        // Run KMeans on TwoDimHard.csv
        KMeans kmTwoDimHard = new KMeans(k, dfTwoDimHard, TwoDimRecord.class);
        kmTwoDimHard.produceTable(new File("out/TwoDimHard.csv"));

        // --------------------------------------------------------------------

        k = 6; // guesstimator for number of clusters existing in wine_quality-red

        // Load in wine_quality-red.csv
        CSVData csvWine = new CSVData("input/wine_quality-red.csv");
        DataFrame dfWine = new WineDataFrame();

        // Parse wine_quality-red.csv to program-friendly format
        dfWine.parseFromCSV(csvWine);

        // Run KMeans on wine_quality-red.csv
        KMeans kmWine = new KMeans(k, dfWine, WineRecord.class);
        kmWine.produceTable(new File("out/wine_quality-red.csv"));
    }
}
