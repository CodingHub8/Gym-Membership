package com.example.gym_membership.Adapters;

public class CardModel {
    private int imageResId;
    private String description;
    private String membershipType; // New field for membership type
    private double price;           // New field for price
    private int duration;        // New field for duration (in days)

    // Constructor
    public CardModel(int imageResId, String membershipType, String description, double price, int duration) {
        this.imageResId = imageResId;
        this.description = description;
        this.membershipType = membershipType;
        this.price = price;
        this.duration = duration;
    }

    // Getters and setters
    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
