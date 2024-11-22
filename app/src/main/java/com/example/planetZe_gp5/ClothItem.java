package com.example.planetZe_gp5;

public class ClothItem {
    private String id;
    private String totalPurchase;
    public ClothItem(String id, String totalPurchase) {
        this.id = id;
        this.totalPurchase = totalPurchase;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTotalPurchase() { return totalPurchase; }
    public void setTotalPurchase(String totalPurchase) { this.totalPurchase = totalPurchase; }
}
