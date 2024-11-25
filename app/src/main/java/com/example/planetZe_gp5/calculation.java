package com.example.planetZe_gp5;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class calculation {
    public calculation() {
    }

    private static DataModel dbModel;

    public static double calculateCarbonFootprint(HashMap<String, Integer> input) {
        return 0.001 * (calculateTransport(input) + calculateFood(input)
                + calculateHousing(input) + calculateConsumption(input));
    }

    public static double calculatePercentage(@NonNull HashMap<String, Double> input, String type) {
        if (!input.containsKey(type) || !input.containsKey("total")) return 0;
        return input.get(type) / input.get("total");
    }

    public static double calculateTransport(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("carOwn")) return 0;
        double cf = 0; //cf = carbon footprint
        if (input.get("carOwn") == 1) {
            cf += calculateCar(input);
        }
        cf += calculatePT(input);
        cf += calculateFlight(input);
        return cf;
    }

    public static double calculateCar(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("carType") || !input.containsKey("driveDistance")) {
            return 0;
        }
        int carType = input.get("carType");
        double ef = 0; //ef = emission factor
        switch (carType) {
            case 1:
                ef = 0.24;
                break;
            case 2:
                ef = 0.27;
                break;
            case 3:
                ef = 0.16;
                break;
            default:
                ef = 0.05;
        }
        return ef * input.get("driveDistance");
    }

    public static double calculatePT(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("ptTime") || !input.containsKey("ptUsage")) {
            return 0;
        }
        double ef = 0; //ef = emission factor (in kg)
        switch (input.get("ptUsage")) {
            case 0:
                return 0;
            case 1:
                switch (input.get("ptTime")) {
                    case 0:
                        ef = 246;
                        break;
                    case 1:
                        ef = 819;
                        break;
                    case 3:
                        ef = 1638;
                        break;
                    case 5:
                        ef = 3071;
                        break;
                    default:
                        ef = 4095;
                }
            default:
                switch (input.get("ptTime")) {
                    case 0:
                        ef = 573;
                        break;
                    case 1:
                        ef = 1911;
                        break;
                    case 3:
                        ef = 3822;
                        break;
                    case 5:
                        ef = 7166;
                        break;
                    default:
                        ef = 9555;
                }
        }
        return ef;
    }

    public static double calculateFlight(HashMap<String, Integer> input) {
        if (!input.containsKey("lh") || !input.containsKey("sh")) {
            return 0;
        }
        int lh = input.get("lh");
        int sh = input.get("sh");
        double ef = 0; //ef = emission factor

        switch (sh) {
            case 1:
                ef += 225;
                break;
            case 3:
                ef += 600;
                break;
            case 6:
                ef += 1200;
                break;
            default:
                ef += 1800;
        }

        switch (lh) {
            case 1:
                ef += 825;
                break;
            case 3:
                ef += 2200;
                break;
            case 6:
                ef += 4400;
                break;
            default:
                ef += 6600;
        }
        return ef;
    }

    public static double calculateFood(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("dietType")) {
            return 0;
        }
        int diet = input.get("dietType");
        // pls switch the order of vegetarian and vegan for simplicity
        if (diet < 4) return 500 * diet + calculateLeftover(input);
        return calculateBeef(input) + calculatePork(input) +
                calculateChicken(input) + calculateSeafood(input) +
                calculateLeftover(input);
    }

    public static double calculateBeef(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("beef")) {
            return 0;
        }
        int beef = input.get("beef");
        switch (beef) {
            case 0:
                return 0;
            case 1:
                return 1300;
            case 3:
                return 1900;
        }
        return 2500;
    }

    public static double calculateChicken(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("chicken")) {
            return 0;
        }
        int chick = input.get("chicken");
        switch (chick) {
            case 0:
                return 0;
            case 1:
                return 450;
            case 3:
                return 860;
        }
        return 1450;
    }

    public static double calculatePork(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("pork")) {
            return 0;
        }
        int pork = input.get("pork");
        switch (pork) {
            case 0:
                return 0;
            case 1:
                return 200;
            case 3:
                return 600;
        }
        return 950;
    }

    public static double calculateSeafood(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("seafood")) {
            return 0;
        }
        int seafood = input.get("seafood");
        switch (seafood) {
            case 0:
                return 0;
            case 1:
                return 150;
            case 3:
                return 500;
        }
        return 800;
    }

    public static double calculateLeftover(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("leftover")) {
            return 0;
        }
        int leftover = input.get("leftover");
        switch (leftover) {
            case 0:
                return 0;
            case 1:
                return 23.4;
            case 2:
                return 70.2;
        }
        return 140.4;
    }

    public static double calculateHousing(@NonNull HashMap<String, Integer> input) {
        if (!input.containsKey("homeType")) return 0;
        dbModel = new DataModel();
        List<String> ef = new ArrayList<String>();
        int type = input.get("homeType");
        if (type == 5) type = 3;
        int ppl = input.getOrDefault("homePplNo", 1);
        int size = input.getOrDefault("homeSize", 1);
        int homeEnergy = input.getOrDefault("heatEnergyType", 1);
        int bill = input.getOrDefault("monthElectricBill", 1);
        int waterHeat = input.getOrDefault("waterHeatEnergyType", 1);
        int renew = input.getOrDefault("renewEnergy", 3);
        int id = (type - 1) * 300;
        id += (ppl - 1) * 25;
        id += (size - 1) * 100;
        id += (homeEnergy - 1);
        id += (bill - 1) * 5;
        String path = "Housing_data/" + id;
        dbModel.readValue(path, ef);

        int cf = Integer.parseInt(ef.get(0));
        if (homeEnergy != waterHeat) cf += 233;
        if (renew == 1) cf -= 6000;
        else if (renew == 2) cf -= 4000;
        return cf;
    }

    public static double calculateConsumption(@NonNull HashMap<String, Integer> input) {
        int clothes = input.getOrDefault("newClothesFreq", 4);
        int eco = input.getOrDefault("ecofriendly", 3);
        int device = input.getOrDefault("devicePurchase", 1);
        int recycle = input.getOrDefault("recycleFreq", 1);
        double cf = 0;
        cf += calculateClothes(clothes);
        if (eco == 1) cf = cf/2;
        else if (eco == 2) cf = cf * 0.7;
        cf += calculateDevice(device);
        cf -= calculateRecycle(recycle, clothes, device);
        return cf;
    }

    public static double calculateClothes(int ans) {
        switch (ans) {
            case 1:
                return 360;
            case 2:
                return 120;
            case 3:
                return 100;
        }
        return 5;
    }

    public static double calculateDevice(int ans){
        switch (ans){
            case 1:
                return 0;
            case 2:
                return 300;
            case 3:
                return 600;
            case 4:
                return 900;
        }
        return 1200;
    }

    public static double calculateRecycle(int ans, int freq, int device){
        if (ans == 1) return 0;
        double cf = 0;
        switch (ans){
            case 2:
                switch (freq){
                    case 1:
                        cf += 54;
                        break;
                    case 2:
                        cf += 15 * 1.2;
                        break;
                    case 3:
                        cf += 15;
                        break;
                    default:
                        cf += 0.75;
                }
                switch (device){
                    case 2:
                        cf += 45;
                        break;
                    case 3:
                        cf += 60;
                        break;
                    case 4:
                        cf += 90;
                        break;
                    default:
                        cf += 120;
                }
            case 3:
                switch (freq){
                    case 1:
                        cf += 108;
                        break;
                    case 2:
                        cf += 30 * 1.2;
                        break;
                    case 3:
                        cf += 30;
                        break;
                    default:
                        cf += 1.5;
                }
                switch (device){
                    case 2:
                        cf += 60;
                        break;
                    case 3:
                        cf += 120;
                        break;
                    case 4:
                        cf += 180;
                        break;
                    default:
                        cf += 240;
                }
            case 4:
                switch (freq){
                    case 1:
                        cf += 180;
                        break;
                    case 2:
                        cf += 50 * 1.2;
                        break;
                    case 3:
                        cf += 50;
                        break;
                    default:
                        cf += 2.5;
                }
                switch (device){
                    case 2:
                        cf += 90;
                        break;
                    case 3:
                        cf += 180;
                        break;
                    case 4:
                        cf += 270;
                        break;
                    default:
                        cf += 360;
                }
        }
        return cf;
    }
}
