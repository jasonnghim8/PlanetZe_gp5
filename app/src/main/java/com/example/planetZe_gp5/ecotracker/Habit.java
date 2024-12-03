package com.example.planetZe_gp5.ecotracker;

public class Habit implements Comparable{

    public String name, description;
    public int category;
    public double offsetValue;
    public double carbonFootprint;
    public boolean selected, visible;
    public String[] replacedHabits;

    public Habit(String name, String description, int category, double carbonFootprint, String[] replacedHabits) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.carbonFootprint = carbonFootprint;
        this.replacedHabits = replacedHabits;
        offsetValue = carbonFootprint;
        visible = true;
        selected = false;
    }

    @Override
    public int compareTo(Object object) {
        Habit other = (Habit) object;
        return (int)(1000*(offsetValue - other.offsetValue));
    }
}
