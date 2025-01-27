package com.example.gym_membership.Models;

import androidx.room.*;

@Entity(tableName = "Membership")
public class Membership {
    @PrimaryKey(autoGenerate = true)
    public int membershipID;

    @ColumnInfo(name = "membershipType")
    public String membershipType;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "duration")
    public int duration; // in days

    public Membership(String membershipType, double price, int duration) {
        this.membershipType = membershipType;
        this.price = price;
        this.duration = duration;
    }
}
