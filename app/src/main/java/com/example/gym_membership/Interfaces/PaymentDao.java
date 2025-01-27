package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.Payment;

@Dao
public interface PaymentDao {
    @Insert
    void insert(Payment payment);

    @Query("SELECT * FROM Payment WHERE paymentID = :paymentID")
    Payment getPaymentById(int paymentID);
}
