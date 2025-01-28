package com.example.gym_membership.Models;

public class AdminApprovalModel {
    private String username;
    private String paymentAmount;
    private String expirationDate;

    public AdminApprovalModel(String username, String paymentAmount, String expirationDate) {
        this.username = username;
        this.paymentAmount = paymentAmount;
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
