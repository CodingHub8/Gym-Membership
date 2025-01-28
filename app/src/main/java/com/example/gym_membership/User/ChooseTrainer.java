package com.example.gym_membership.User;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityTrainerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChooseTrainer extends AppCompatActivity {
    UserActivityTrainerBinding binding;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = UserActivityTrainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_trainer);
        new PageNavigator(this, bottomNav);
    }
}

