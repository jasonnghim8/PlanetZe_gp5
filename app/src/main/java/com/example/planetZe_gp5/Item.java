package com.example.planetZe_gp5;

public class Item {

    private String id;
    private String user;
    private String footprint;

    public Item() {}

    public Item(String id, String user, String footprint) {
        this.id = id;
        this.user = user;
        this.footprint = footprint;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getFootprint() { return footprint; }
    public void setFootprint(String footprint) { this.footprint = footprint; }
}
