package com.example.planetZe_gp5;

public class BillsItem {
    private String id;
    private String type, price;
    public BillsItem(String id, String type, String price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
