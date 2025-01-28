package com.example.gym_membership.Models;

import androidx.annotation.Nullable;
import androidx.room.*;

@Entity(tableName = "Payment",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "userID",
                childColumns = "userID",
                onDelete = ForeignKey.CASCADE))
public class Payment {
    @PrimaryKey(autoGenerate = true)
    public int paymentID;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "paymentStatus")
    public String paymentStatus;

    @ColumnInfo(name = "paymentAmount")
    public double paymentAmount;

    @ColumnInfo(name = "expirationDate")
    public String expirationDate;

    @Nullable
    @ColumnInfo(name = "userID")
    public Integer userID;

    // Updated constructor without paymentID
    public Payment(String username, String paymentStatus, double paymentAmount, String expirationDate, @Nullable Integer userID) {
        this.username = username;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.expirationDate = expirationDate;
        this.userID = userID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getUsername() {
        return username;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Nullable
    public Integer getUserID() {
        return userID;
    }
}
