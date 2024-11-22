package com.example.planetZe_gp5;

public class ElectronicsItem {
    private String id;
    private String electronicsType, totalPurchases;
    public ElectronicsItem(String id, String electronicsType, String totalPurchases) {
        this.id = id;
        this.electronicsType = electronicsType;
        this.totalPurchases = totalPurchases;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getElectronicsType() { return electronicsType; }
    public void setElectronicsType(String electronicsType) { this.electronicsType = electronicsType; }
    public String getTotalPurchases() { return totalPurchases; }
    public void setTotalPurchases(String totalPurchases) { this.totalPurchases = totalPurchases; }
}
