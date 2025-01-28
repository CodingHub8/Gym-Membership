package com.example.gym_membership.User;

import static java.lang.String.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Interfaces.UserDao;
import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.Models.User;
import com.example.gym_membership.databinding.UserActivityPaymentBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentProcess extends AppCompatActivity {
    UserActivityPaymentBinding binding;
    Intent intent;
    DBGymMembership db;
    ExecutorService executorService;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = UserActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = DBGymMembership.getInstance(this);
        executorService = Executors.newSingleThreadExecutor();

        intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);
        String username = intent.getStringExtra("username");
        String membershipType = intent.getStringExtra("membershipType");
        double price = intent.getDoubleExtra("price", 0.0);
//        int duration = intent.getIntExtra("duration", 0);

        binding.txtSelectedMembership.setText(membershipType);
        binding.txtPrice.setText(format("RM %.2f", price));

        binding.btnCancelPayment.setOnClickListener(view -> {
            finish();
        });

        binding.btnConfirmPayment.setOnClickListener(view -> {
            Payment payment = new Payment(username, "Pending", price, "2025-12-31", userId);

            executorService.execute(() -> {
                try {
//                    long result = db.paymentDao().insert(payment);
                    long result = db.userDao().updateMembershipStatus(userId, "Active");
                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "Registration Failed: Insert returned 0", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Error during registration: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(e.getMessage());
                    });
                }
            });
        });
    }
}
