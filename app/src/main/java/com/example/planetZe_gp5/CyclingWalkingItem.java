package com.example.planetZe_gp5;

public class CyclingWalkingItem {
    private String id;
    private String distance;

    public CyclingWalkingItem(String id, String distance) {
        this.id = id;
        this.distance = distance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDistance() { return distance; }
    public void setDistance(String distance) { this.distance = distance; }

}
