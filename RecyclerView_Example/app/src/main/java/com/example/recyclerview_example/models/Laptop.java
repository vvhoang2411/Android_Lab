package com.example.recyclerview_example.models;

public class Laptop {
    public String imageUrl;
    public String laptopName;

    public double getLaptopRating() {
        return laptopRating;
    }

    public void setLaptopRating(double laptopRating) {
        this.laptopRating = laptopRating;
    }

    public double getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(double laptopPrice) {
        this.laptopPrice = laptopPrice;
    }

    public String getLaptopDescription() {
        return laptopDescription;
    }

    public void setLaptopDescription(String laptopDescription) {
        this.laptopDescription = laptopDescription;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getImageUrl() {
        return  imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String laptopDescription;
    public double laptopPrice;
    public double laptopRating;

    public Laptop(String imageUrl, String laptopName, String laptopDescription, double laptopPrice, double laptopRating) {
        this.imageUrl = imageUrl;
        this.laptopName = laptopName;
        this.laptopDescription = laptopDescription;
        this.laptopPrice = laptopPrice;
        this.laptopRating = laptopRating;
    }
}
