package com.example.leez;

import java.io.Serializable;
import com.google.firebase.firestore.PropertyName;

public class ProductInfo implements Serializable{
    private String name;
    private String quantity;
    private String price;

    public ProductInfo() {
        // Required no-argument constructor for Firestore
    }

    public ProductInfo(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    @PropertyName("quantity")
    public String getQuantity() {
        return quantity;
    }

    @PropertyName("price")
    public String getPrice() {
        return price;
    }
}
