package com.example.buiviet_2123110186;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String category;      // dùng để lọc
    private String colors;
    private String price;
    private String discount;
    private int imageResId;

    // Constructor đầy đủ
    public Product(String name, String category, String colors, String price, String discount, int imageResId) {
        this.name = name;
        this.category = category;
        this.colors = colors;
        this.price = price;
        this.discount = discount;
        this.imageResId = imageResId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getColors() {
        return colors;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }


}

