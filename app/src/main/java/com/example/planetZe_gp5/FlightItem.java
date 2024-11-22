package com.example.planetZe_gp5;

public class FlightItem {
    private String id;
    private String totalFlight, distance;
    public FlightItem(String id, String totalFlight, String distance) {
        this.id = id;
        this.totalFlight = totalFlight;
        this.distance = distance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTotalFlight() { return totalFlight; }
    public void setTotalFlight(String totalFlight) { this.totalFlight = totalFlight; }
    public String getDistance() { return distance; }
    public void setDistance(String distance) { this.distance = distance; }
}
