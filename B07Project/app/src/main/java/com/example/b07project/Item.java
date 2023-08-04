package com.example.b07project;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class Item {

    static private DatabaseReference db = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference();
    private String id;
    private String name;
    private double price;
    private String description;

    public Item(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = db.push().getKey();
    }

    public Item(String name, double price, String description, String id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public boolean addItem(String username) {
        // ret value determine success (T) or fail (F)
        // try to add to database, if anything happens return false
        try {
            db.child("Owners").child(username).child("products").child(id).setValue(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean removeItem(String owner_username) {
        // look for equal item to this (why we override equals)
        // remove from database (under Owners, products)
        // ret value for success (T) or fail (F)
        return false;
    }
}
