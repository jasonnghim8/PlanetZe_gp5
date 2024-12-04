package com.example.planetZe_gp5.ecotracker;

public class Habit implements Comparable{

    public String name, description;
    public int category, logCount;;
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
        this.logCount = 0;
        visible = true;
        selected = false;
    }
    public int getLogCount() { return logCount; }
    public void setLogCount(int logCount) { this.logCount = logCount; }

    @Override
    public int compareTo(Object object) {
        Habit other = (Habit) object;
        return (int)(1000*(offsetValue - other.offsetValue));
    }
}
