package com.example.gym_membership.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gym_membership.Adapters.CardAdapter;
import com.example.gym_membership.Adapters.CardModel;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        binding.txtName.setText(intent.hasExtra("username") ? intent.getStringExtra("username") : "");
        binding.txtPhone.setText(intent.hasExtra("phone") ? intent.getStringExtra("phone") : "");
        binding.txtEmail.setText(intent.hasExtra("email") ? intent.getStringExtra("email") : "");

        if (Objects.requireNonNull(intent.getStringExtra("membershipStatus")).equals("Inactive")){
            binding.txtMembershipNotify.setText("You currently have no membership!");
        } else if (Objects.requireNonNull(intent.getStringExtra("membershipStatus")).equals("Active")){
            int membershipID = intent.getIntExtra("membershipID", 1);

            if (membershipID == 1){
                binding.txtMembershipNotify.setText("Bronze Membership Active");
            } else if (membershipID == 2){
                binding.txtMembershipNotify.setText("Silver Membership Active");
            } else if (membershipID == 3){
                binding.txtMembershipNotify.setText("Gold Membership Active");
            }
        }
        displayMemberships();
    }

    private void displayMemberships(){
        List<CardModel> cardList = new ArrayList<>();
        cardList.add(new CardModel(R.drawable.bronze_star, "Bronze", "RM28.00\n30 Days Validity", 28.00, 30));
        cardList.add(new CardModel(R.drawable.silver_star, "Silver", "RM50.00\n60 Days Validity", 50.00, 60));
        cardList.add(new CardModel(R.drawable.gold_star, "Gold", "RM72.00\n90 Days Validity", 72.00, 90));

        CardAdapter adapter = new CardAdapter(getApplicationContext(), cardList);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
    }
}
