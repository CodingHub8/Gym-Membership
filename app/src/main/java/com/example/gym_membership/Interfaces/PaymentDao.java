package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.Payment;

import java.util.List;


@Dao
public interface PaymentDao {
    @Insert
    long insert(Payment payment);

    @Query("SELECT * FROM Payment WHERE paymentID = :paymentID")
    Payment getPaymentById(int paymentID);

    // Method to fetch all payments
    @Query("SELECT * FROM Payment")
    List<Payment> getAllPayments();

    @Query("SELECT * FROM Payment WHERE username LIKE '%' || :username || '%'")
    List<Payment> searchPaymentsByUsername(String username);
}


