package com.example.planetZe_gp5;

public class OtherPurchasesItem {
    private String id;
    private String type, totalPurchases;
    public OtherPurchasesItem(String id, String type, String totalPurchases) {
        this.id = id;
        this.type = type;
        this.totalPurchases = totalPurchases;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTotalPurchases() { return totalPurchases; }
    public void setTotalPurchases(String totalPurchases) { this.totalPurchases = totalPurchases; }
}
