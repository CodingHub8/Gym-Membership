package com.example.gym_membership.Models;

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

    @ColumnInfo(name = "userID")
    public int userID;

    // Updated constructor without paymentID
    public Payment(String username, String paymentStatus, double paymentAmount, String expirationDate, int userID) {
        this.username = username;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.expirationDate = expirationDate;
        this.userID = userID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
