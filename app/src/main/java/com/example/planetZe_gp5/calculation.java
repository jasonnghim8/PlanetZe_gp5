package com.example.planetZe_gp5;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class calculation {
    public calculation(){}

    public static double calculateCarbonFootprint(HashMap<String, Integer> input){
        return 0.001 * (calculateTransport(input) + calculateFood(input)
                + calculateHousing(input) + calculateConsumption(input));
    }

    public static double calculateTransport(HashMap<String, Integer> input){
        if (!input.containsKey("carOwn")) return 0;
        double cf = 0; //cf = carbon footprint
        if (input.get("carOwn") == 1){
            cf += calculateCar(input);
        }
        cf += calculatePT(input);
        cf += calculateFlight(input);
        return cf;
    }

    public static double calculateCar(HashMap<String, Integer> input){
        if (!input.containsKey("carType") || !input.containsKey("driveDistance")){
            return 0;
        }
        int carType = input.get("carType");
        double ef = 0; //ef = emission factor
        if (carType == 1) ef = 0.24;
        if (carType == 2) ef = 0.27;
        if (carType == 3) ef = 0.16;
        if (carType == 4) ef = 0.05;
        return ef * input.get("driveDistance");
    }

    public static double calculatePT(HashMap<String, Integer> input){
        if (!input.containsKey("ptTime") || !input.containsKey("ptUsage")){
            return 0;
        }
        int freq = input.get("ptUsage");
        int time = input.get("ptTime");
        double ef = 0; //ef = emission factor (in kg)
        if (freq == 0) ef = 0;
        if (freq == 1) {
            if (time == 0) ef = 246;
            else if (time == 1) ef = 819;
            else if (time == 3) ef = 1638;
            else if (time == 5) ef = 3071;
            else ef = 4095;
        }
        if (freq >= 3) {
            if (time == 0) ef = 573;
            else if (time == 1) ef = 1911;
            else if (time == 3) ef = 3822;
            else if (time == 5) ef = 7166;
            else ef = 9555;
        }
        return ef;
    }

    public static double calculateFlight(HashMap<String, Integer> input){
        if (!input.containsKey("lh") || !input.containsKey("sh")){
            return 0;
        }
        int lh = input.get("lh");
        int sh = input.get("sh");
        double ef = 0; //ef = emission factor

        if (sh == 1) ef += 225;
        else if (sh == 3) ef += 600;
        else if (sh == 6) ef += 1200;
        else if (sh == 10) ef += 1800;

        if (lh == 1) ef += 825;
        else if (lh == 3) ef += 2200;
        else if (lh == 6) ef += 4400;
        else if (lh == 10) ef += 6600;
        return ef;
    }

    public static double calculateFood(@NonNull HashMap<String, Integer> input){
        if (!input.containsKey("dietType")){
            return 0;
        }
        int diet = input.get("dietType");
        // pls switch the order of vegetarian and vegan for simplicity
        if (diet < 4) return 500 * diet + calculateLeftover(input);
        return calculateBeef(input) + calculatePork(input) +
                calculateChicken(input) + calculateSeafood(input)+
                calculateLeftover(input);
    }

    public static double calculateBeef(@NonNull HashMap<String, Integer> input){
        if (!input.containsKey("beef")){
            return 0;
        }
        int beef = input.get("beef");
        if (beef == 0) return 0;
        if (beef == 1) return 1300;
        if (beef == 3) return 1900;
        return 2500;
    }
    public static double calculateChicken(@NonNull HashMap<String, Integer> input){
        if (!input.containsKey("chicken")){
            return 0;
        }
        int chick = input.get("chicken");
        if (chick == 0) return 0;
        if (chick == 1) return 450;
        if (chick == 3) return 860;
        return 1450;
    }
    public static double calculatePork(@NonNull HashMap<String, Integer> input){
        if (!input.containsKey("pork")){
            return 0;
        }
        int pork = input.get("pork");
        if (pork == 0) return 0;
        if (pork == 1) return 200;
        if (pork == 3) return 600;
        return 950;
    }
    public static double calculateSeafood(@NonNull HashMap<String, Integer> input){
        if (!input.containsKey("seafood")){
            return 0;
        }
        int seafood = input.get("seafood");
        if (seafood == 0) return 0;
        if (seafood == 1) return 150;
        if (seafood == 3) return 500;
        return 800;
    }
    public static double calculateLeftover(@NonNull HashMap<String, Integer> input){
        if (!input.containsKey("leftover")){
            return 0;
        }
        int leftover = input.get("leftover");
        if (leftover == 0) return 0;
        if (leftover == 1) return 23.4;
        if (leftover == 2) return 70.2;
        return 140.4;
    }

    public static double calculateHousing(HashMap<String, Integer> input){
        return 0;
    }

    public static double calculateConsumption(HashMap<String, Integer> input){
        return 0;
    }
}
