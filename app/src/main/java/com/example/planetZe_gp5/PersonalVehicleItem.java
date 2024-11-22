package com.example.planetZe_gp5;

public class PersonalVehicleItem {
    private String id;
    private String distanceDriven;
    private String vehicleType;

    public PersonalVehicleItem(String id, String distanceDriven, String vehicleType) {
        this.id = id;
        this.distanceDriven = distanceDriven;
        this.vehicleType = vehicleType;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDistanceDriven() { return distanceDriven; }
    public void setDistanceDriven(String distanceDriven) { this.distanceDriven = distanceDriven; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
}
