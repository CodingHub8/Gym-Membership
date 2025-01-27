package com.example.gym_membership.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.databinding.AdminActivityHomeBinding;

public class Home extends AppCompatActivity {
    AdminActivityHomeBinding binding;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = AdminActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
