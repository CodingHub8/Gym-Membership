package com.example.gym_membership.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gym_membership.Adapters.*;
import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.User;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.*;

public class Home extends AppCompatActivity {
    UserActivityHomeBinding binding;
    Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = UserActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setStatusText();
        displayMemberships();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);
        new PageNavigator(this, bottomNav);
    }

    @SuppressLint("SetTextI18n")
    private void setStatusText(){
        SharedPreferences sharedPref = getSharedPreferences("Gym_Membership", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String username = sharedPref.getString("username", "Guest");
        String phone = sharedPref.getString("phone", "Phone");
        String email = sharedPref.getString("email", "Email");
        String membershipStatus = sharedPref.getString("membershipStatus", "Inactive");

        // Set the values to the corresponding TextViews
        binding.txtName.setText(username); // Set the username
        binding.txtPhone.setText(phone);   // Set the phone
        binding.txtEmail.setText(email);   // Set the email

        // Get userId from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("Gym_Membership", MODE_PRIVATE);
        int userId = preferences.getInt("userId", -1);

        // Access database and user information
        DBGymMembership db = DBGymMembership.getInstance(this);
        User user = db.userDao().getUserById(userId);

        // Update membership notification
        if ("Inactive".equals(membershipStatus) || user.membershipStatus == null || user.membershipID == null) {
            binding.txtMembershipNotify.setText("You currently have no membership!");
        } else if ("Pending".equals(user.membershipStatus)){
            binding.txtMembershipNotify.setText("Your membership is pending approval");
        }else if ("Active".equals(user.membershipStatus)){
            int membershipID = user.membershipID;
            switch (membershipID){
                case 1:
                    binding.txtMembershipNotify.setText("You currently have Bronze membership");
                    break;
                case 2:
                    binding.txtMembershipNotify.setText("You currently have Silver membership");
                    break;
                case 3:
                    binding.txtMembershipNotify.setText("You currently have Gold membership");
                    break;
                default:
                    binding.txtMembershipNotify.setText("");
            }
        }
    }

    private void displayMemberships(){// insert data into cards (not related to database)
        List<CardModel> cardList = new ArrayList<>();
        cardList.add(new CardModel(R.drawable.bronze_star, "Bronze", "RM28.00\n30 Days Validity", 28.00, 30));
        cardList.add(new CardModel(R.drawable.silver_star, "Silver", "RM50.00\n60 Days Validity", 50.00, 60));
        cardList.add(new CardModel(R.drawable.gold_star, "Gold", "RM72.00\n90 Days Validity", 72.00, 90));

        CardAdapter adapter = new CardAdapter(getApplicationContext(), cardList);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
    }
}