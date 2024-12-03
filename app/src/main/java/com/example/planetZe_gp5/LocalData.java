package com.example.planetZe_gp5;

import com.example.planetZe_gp5.ecotracker.Habit;

import java.util.ArrayList;
import java.util.List;

public final class LocalData {
    private static String userid;

    // ACF data.

    public static int num_question = 21;

    public static String[] ACFquestion = {
            "Personal Vehicle Use: 1. Do you own or regularly use a car?",
            "Personal Vehicle Use: 2. What type of car do you drive?",
            "Personal Vehicle Use: 3. How many kilometers/miles do you drive per year?",
            "Public Transportation: 4. How often do you use public transportation (bus, train,\n" +
                    "subway)?",
            "Public Transportation: 5. Question: How much time do you spend on public transport\n" +
                    "per week (bus, train, subway)?",
            "Air Travel: 6. How many short-haul flights (less than 1,500 km / 932 miles) have you\n" +
                    "taken in the past year?",
            "Air Travel: 7. How many long-haul flights (more than 1,500 km / 932 miles) have you\n" +
                    "taken in the past year?",
            "8. What best describes your diet?",
            "9.1 How often do you eat the following animal-based products: Beef?",
            "9.2 How often do you eat the following animal-based products: Pork?",
            "9.3 How often do you eat the following animal-based products: Chicken?",
            "9.4 How often do you eat the following animal-based products: Fish/Seafood?",
            "10. How often do you waste food or throw away uneaten leftovers?",
            "11. What type of home do you live in?",
            "12. How many people live in your household?",
            "13. What is the size of your home?",
            "14. What type of energy do you use to heat your home?",
            "15. What is your average monthly electricity bill?",
            "16. What type of energy do you use to heat water in your home?",
            "17. Do you use any renewable energy sources for electricity or heating (e.g., solar,\n" +
                    "wind)?",
            "18. How often do you buy new clothes?",
            "19. Do you buy second-hand or eco-friendly products?",
            "20. How many electronic devices (phones, laptops, etc.) have you purchased in the\n" +
                    "past year?",
            "21. How often do you recycle?"
    };

    public static String[][] ACFanswer = {
            {"Yes", "No"},
            {"Gasoline" , "Diesel" , "Hybrid" , "Electric" , "I don’t know"},
            {"Up to 5,000 km (3,000 miles)", "5,000–10,000 km (3,000–6,000 miles)",
                    "10,000–15,000 km (6,000–9,000 miles)",
                    "15,000–20,000 km (9,000–12,000 miles)",
                    "20,000–25,000 km (12,000–15,000 miles)",
                    "More than 25,000 km (15,000 miles)"},
            {"Never", "Occasionally (1-2 times/week)", "Frequently (3-4 times/week)",
                    "Always (5+ times/week)"},
            {"Under 1 hour", "1-3 hours", "3-5 hours", "5-10 hours", "More than 10 hours"},
            {"None", "1-2 flights", "3-5 flights", "6-10 flights", "More than 10 flights"},
            {"None", "1-2 flights", "3-5 flights", "6-10 flights", "More than 10 flights"},
            {"Vegan", "Vegetarian", "Pescatarian (fish/seafood)",
                    "Meat-based (eat all types of animal products)"},
            {"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"},
            {"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"},
            {"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"},
            {"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"},
            {"Never", "Rarely", "Occasionally", "Frequently"},
            {"Detached house", "Semi-detached house", "Townhouse", "Condo/Apartment", "Other"},
            {"1", "2", "3-4", "5 or more"},
            {"Under 1000 sq. ft.", "1000-2000 sq. ft.", "Over 2000 sq. ft."},
            {"Natural Gas", "Electricity", "Oil", "Propane", "Wood", "Other"},
            {"Under $50", "$50-$100", "$100-$150", "$150-$200", "Over $200"},
            {"Natural Gas", "Electricity", "Oil", "Propane", "Solar", "Other"},
            {"Yes, primarily (more than 50% of energy use)",
                    "Yes, partially (less than 50% of energy use)", "No"},
            {"Monthly", "Quarterly", "Annually", "Rarely"},
            {"Yes, regularly", "Yes, occasionally", "No"},
            {"None", "1", "2", "3 or more"},
            {"Never", "Occasionally", "Frequently", "Always"}
    };//have created four answer for question 9 if number 8 chose meat

    // Eco tracker data.
    public static final String[][][] ETQuestions = {
            {
                    {"Distance driven in km", "Time spent in hours", "Distance driven in km"},
                    {"010", "011", "012"},
                    {"020", "021", "022"}
            },
            {
                    {"Number of servings consumed"},
                    {"110", "111", "112"},
                    {"120", "121", "122"}
            },
            {
                    {"Number of clothing items purchased", "Number of devices purchased", "Number of purchases", "Amount of the bill in dollars"},
                    {"210", "211", "212"},
                    {"220", "221", "222"}
            }};

    public static final String[][][] ETValues = {
            {
                    {"Car driven km:", "001", "002"},
                    {"010", "011", "012"},
                    {"020", "021", "022"}
            },
            {
                    {"Number of servings consumed"},
                    {"110", "111", "112"},
                    {"120", "121", "122"}
            },
            {
                    {"Number of clothing items purchased", "Number of devices purchased", "Number of purchases", "Amount of the bill in dollars"},
                    {"210", "211", "212"},
                    {"220", "221", "222"}
            }};

    public static String ETGetValue(String ijk) {
        int i = (int)ijk.charAt(0) - '0';
        int j = (int)ijk.charAt(1) - '0';
        int k = (int)ijk.charAt(2) - '0';
        return LocalData.ETValues[i][j][k];
    }

    public static final int TRANSPORTATION = 0;
    public static final int FOOD = 1;
    public static final int CONSUMPTION = 2;
    public static List<Habit> habitList;
    public static List<Habit> allHabitsList;

    public static void initializeHabitList() {
        if (habitList != null) {
            return;
        }
        habitList = new ArrayList<>();
        habitList.add(new Habit("name1", "description1", FOOD, 10, new String[]{"010", "011"}));
        habitList.add(new Habit("name2", "description2", TRANSPORTATION, 10, new String[]{"000", "001"}));
        allHabitsList = new ArrayList<>(habitList);
    }

    public static long calendarDate;

    public static String getUserid() {
        return LocalData.userid;
    }

    public static void setUserid(String userid) {
        LocalData.userid = userid;
        DataModel.getInstance().setUserPath(userid);
    }
}
