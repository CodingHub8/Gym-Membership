package com.example.gym_membership.User;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.databinding.UserActivityPaymentBinding;

public class PaymentProcess extends AppCompatActivity {
    UserActivityPaymentBinding binding;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = UserActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
