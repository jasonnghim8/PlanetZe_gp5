package com.example.planetZe_gp5;

public class PublicTransportationItem {
    private String id;
    private String timeSpent, transportationType;
    public PublicTransportationItem(String id, String timeSpent, String transportationType) {
        this.id = id;
        this.timeSpent = timeSpent;
        this.transportationType = transportationType;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTimeSpent() { return timeSpent; }
    public void setTimeSpent(String timeSpent) { this.timeSpent = timeSpent; }
    public String getTransportationType() { return transportationType; }
    public void setTransportationType(String transportationType) { this.transportationType = transportationType; }
}
