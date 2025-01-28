package com.example.gym_membership.User;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityBmiBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BMIProcess extends AppCompatActivity {
    UserActivityBmiBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserActivityBmiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_bmi);
        new PageNavigator(this, bottomNav);
    }
}
