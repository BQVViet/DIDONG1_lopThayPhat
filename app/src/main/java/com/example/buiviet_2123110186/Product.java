package com.example.buiviet_2123110186;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String category;
    private String image;       // tên file ảnh (không bao gồm đuôi .png)
    private double price;
    private double rating;
    private String description;

    // Constructor
    public Product(String name, String category, String image, double price, double rating, String description) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    // Getter
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getImage() { return image; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getDescription() { return description; }

    // Optional: Setter nếu bạn muốn chỉnh sau
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setImage(String image) { this.image = image; }
    public void setPrice(double price) { this.price = price; }
    public void setRating(double rating) { this.rating = rating; }
    public void setDescription(String description) { this.description = description; }
}

