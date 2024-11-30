package com.example.planetZe_gp5.ecotracker;

public class EcoTrackerData {
    static final String[][] keyQuestions = {
            {"Distance driven in km", "Time spent in hours", "Distance driven in km"},
            {"Number of servings consumed"},
            {"Number of clothing items purchased", "Number of devices purchased", "Number of purchases", "Amount of the bill in dollars"}};

    public static String getKeyString(int i, int j) {
        // some categories have the same question despite j spinner position
        if (j >= EcoTrackerData.keyQuestions[i].length) {
            j = EcoTrackerData.keyQuestions[i].length - 1;
        }
        if (i >= 0 && j >= 0) {
            return EcoTrackerData.keyQuestions[i][j];
        }
        return "";
    }
    public static long calendarDate;

    public static String getKeyString(String ijk) {
        int i = (int)ijk.charAt(0) - '0';
        int j = (int)ijk.charAt(1) - '0';
        return EcoTrackerData.getKeyString(i, j);
    }
}
