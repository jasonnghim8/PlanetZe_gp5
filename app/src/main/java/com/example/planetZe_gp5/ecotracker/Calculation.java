package com.example.planetZe_gp5.ecotracker;

public class Calculation {
    public static double calculateFootprint(String id, String amount) {
        double footprint = Integer.parseInt(amount);
        // please implement calculation of footprint based on string id and amount.
        return footprint*10;
    }
}
