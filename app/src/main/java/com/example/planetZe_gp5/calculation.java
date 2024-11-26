package com.example.planetZe_gp5;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class calculation {
    public calculation() {
    }

    private  DataModel dbModel;

    String userid;
    public calculation(String userid){
        this.userid = userid;
        dbModel = new DataModel();
    }
    public  double calculateCarbonFootprint(HashMap<Integer, Integer> input) {
        double cf = (calculateTransport(input) + calculateFood(input)
                + calculateHousing(input) + calculateConsumption(input));
        String path = "/Users/" + userid + "/annualCarbonFootprint";
        dbModel.writeData(path, cf);
        return cf;
    }

    public  double calculatePercentage(String type) {
        String path = "/Users/" + userid + "/annualCarbonFootprint/" + type;
        String pathTotal = "/Users/" + userid + "/annualCarbonFootprint";
        List<String> types = new ArrayList<String>();
        dbModel.readData(path, types);
        dbModel.readData(pathTotal, types);
        double typeData = Double.parseDouble(types.get(0));
        double total = Double.parseDouble(types.get(1));
        return typeData/total;
    }

    public  double calculateTransport(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(1)) return 0;
        double cf = 0; //cf = carbon footprint
        if (input.get(1) == 1) {
            cf += calculateCar(input);
        }
        cf += calculatePT(input);
        cf += calculateFlight(input);
        String path = "/Users/" + userid + "/annualCarbonFootprint/transportation";
        cf = cf * 0.001;
        dbModel.writeData(path, cf);
        return cf;
    }

    public  double calculateCar(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(2) || !input.containsKey(3)) {
            return 0;
        }
        int carType = input.get(2);
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
        return ef * input.get(3);
    }

    public  double calculatePT(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(5) || !input.containsKey(4)) {
            return 0;
        }
        double ef = 0; //ef = emission factor (in kg)
        switch (input.get(5)) {
            case 0:
                return 0;
            case 1:
                switch (input.get(4)) {
                    case 1:
                        ef = 246;
                        break;
                    case 2:
                        ef = 819;
                        break;
                    case 3:
                        ef = 1638;
                        break;
                    case 4:
                        ef = 3071;
                        break;
                    default:
                        ef = 4095;
                }
            default:
                switch (input.get(4)) {
                    case 1:
                        ef = 573;
                        break;
                    case 2:
                        ef = 1911;
                        break;
                    case 3:
                        ef = 3822;
                        break;
                    case 4:
                        ef = 7166;
                        break;
                    default:
                        ef = 9555;
                }
        }
        return ef;
    }

    public  double calculateFlight(HashMap<Integer, Integer> input) {
        if (!input.containsKey(7) || !input.containsKey(6)) {
            return 0;
        }
        int lh = input.get(7);
        int sh = input.get(6);
        double ef = 0; //ef = emission factor

        switch (sh) {
            case 1:
                ef += 225;
                break;
            case 2:
                ef += 600;
                break;
            case 3:
                ef += 1200;
                break;
            default:
                ef += 1800;
        }

        switch (lh) {
            case 1:
                ef += 825;
                break;
            case 2:
                ef += 2200;
                break;
            case 3:
                ef += 4400;
                break;
            default:
                ef += 6600;
        }
        return ef;
    }

    public  double calculateFood(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(8)) {
            return 0;
        }
        int diet = input.get(8);
        // pls switch the order of vegetarian and vegan for simplicity
        if (diet < 4) return 500 * diet + calculateLeftover(input);
        double cf = calculateBeef(input) + calculatePork(input) +
                calculateChicken(input) + calculateSeafood(input) +
                calculateLeftover(input);
        cf = cf * 0.001;
        String path = "/Users/" + userid + "/annualCarbonFootprint/Food";
        dbModel.writeData(path, cf);
        return cf;
    }

    public  double calculateBeef(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(9)) {
            return 0;
        }
        int beef = input.get(9);
        switch (beef) {
            case 0:
                return 0;
            case 1:
                return 1300;
            case 2:
                return 1900;
        }
        return 2500;
    }

    public  double calculateChicken(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(10)) {
            return 0;
        }
        int chick = input.get(10);
        switch (chick) {
            case 0:
                return 0;
            case 1:
                return 450;
            case 2:
                return 860;
        }
        return 1450;
    }

    public  double calculatePork(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(11)) {
            return 0;
        }
        int pork = input.get(11);
        switch (pork) {
            case 0:
                return 0;
            case 1:
                return 200;
            case 2:
                return 600;
        }
        return 950;
    }

    public  double calculateSeafood(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(12)) {
            return 0;
        }
        int seafood = input.get(12);
        switch (seafood) {
            case 0:
                return 0;
            case 1:
                return 150;
            case 2:
                return 500;
        }
        return 800;
    }

    public  double calculateLeftover(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(13)) {
            return 0;
        }
        int leftover = input.get(13);
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

    public  double calculateHousing(@NonNull HashMap<Integer, Integer> input) {
        if (!input.containsKey(14)) return 0;
        dbModel = new DataModel();
        List<String> ef = new ArrayList<String>();
        int type = input.get(14);
        if (type == 5) type = 3;
        int ppl = input.getOrDefault(15, 1);
        int size = input.getOrDefault(16, 1);
        int homeEnergy = input.getOrDefault(17, 1);
        int bill = input.getOrDefault(18, 1);
        int waterHeat = input.getOrDefault(19, 1);
        int renew = input.getOrDefault(20, 3);
        int id = (type - 1) * 300;
        id += (ppl - 1) * 25;
        id += (size - 1) * 100;
        id += (homeEnergy - 1);
        id += (bill - 1) * 5;
        String path = "Housing_data/" + id;
        dbModel.readValue(path, ef);

        double cf = Double.parseDouble(ef.get(0));
        if (homeEnergy != waterHeat) cf += 233;
        if (renew == 1) cf -= 6000;
        else if (renew == 2) cf -= 4000;
        cf = cf * 0.001;
        String path2 = "/Users/" + userid + "/annualCarbonFootprint/Housing";
        dbModel.writeData(path2, cf);
        return cf;
    }

    public  double calculateConsumption(@NonNull HashMap<Integer, Integer> input) {
        int clothes = input.getOrDefault(21, 4);
        int eco = input.getOrDefault(22, 3);
        int device = input.getOrDefault(23, 1);
        int recycle = input.getOrDefault(24, 1);
        double cf = 0;
        cf += calculateClothes(clothes);
        if (eco == 1) cf = cf/2;
        else if (eco == 2) cf = cf * 0.7;
        cf += calculateDevice(device);
        cf -= calculateRecycle(recycle, clothes, device);
        cf = cf * 0.001;
        String path = "/Users/" + userid + "/annualCarbonFootprint/Consumption";
        dbModel.writeData(path, cf);
        return cf;
    }

    public  double calculateClothes(int ans) {
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

    public  double calculateDevice(int ans){
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

    public  double calculateRecycle(int ans, int freq, int device){
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
