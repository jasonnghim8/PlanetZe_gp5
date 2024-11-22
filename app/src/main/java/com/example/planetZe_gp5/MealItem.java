package com.example.planetZe_gp5;

public class MealItem {
    private String id;
    private String mealType, totalServings;
    public MealItem(String id, String mealType, String totalServings) {
        this.id = id;
        this.mealType = mealType;
        this.totalServings = totalServings;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }
    public String getTotalServings() { return totalServings; }
    public void setTotalServings(String totalServings) { this.totalServings = totalServings; }
}
