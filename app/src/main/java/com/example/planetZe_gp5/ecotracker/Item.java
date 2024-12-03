package com.example.planetZe_gp5.ecotracker;

public class Item {

    public String key;
    public String value;
    public boolean changed;
    public boolean selected;

    public Item() {}

    public Item(String key, String value) {
        this.key = key;
        this.value = value;
        changed = false;
        selected = false;
    }
}
