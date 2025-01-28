package com.example.gym_membership.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityTrainerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChooseTrainer extends AppCompatActivity {
    UserActivityTrainerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = UserActivityTrainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_trainer);
        new PageNavigator(this, bottomNav);

        // Trainer Data
        Trainer[] trainers = new Trainer[]{
                new Trainer("John Doe", 30, 5, R.drawable.profile),
                new Trainer("Jane Smith", 28, 3, R.drawable.woman),
                new Trainer("Mike Johnson", 35, 10, R.drawable.boy)
        };

        // Populate the LinearLayout with trainer profiles
        LinearLayout trainerContainer = findViewById(R.id.trainer_container);
        for (Trainer trainer : trainers) {
            View trainerView = LayoutInflater.from(this).inflate(R.layout.user_activity_trainer_item, trainerContainer, false);

            // Set trainer details
            ImageView profileImage = trainerView.findViewById(R.id.iv_trainer_picture);
            profileImage.setImageResource(trainer.getImageResId());

            TextView nameText = trainerView.findViewById(R.id.tv_trainer_name);
            nameText.setText(trainer.getName());

            TextView ageText = trainerView.findViewById(R.id.tv_trainer_age);
            ageText.setText("Age: " + trainer.getAge());

            TextView experienceText = trainerView.findViewById(R.id.tv_trainer_experience);
            experienceText.setText("Experience: " + trainer.getYearsOfExperience() + " years");

            // Add to the container
            trainerContainer.addView(trainerView);
        }
    }

    // Trainer Model Class
    static class Trainer {
        private final String name;
        private final int age;
        private final int yearsOfExperience;
        private final int imageResId;

        public Trainer(String name, int age, int yearsOfExperience, int imageResId) {
            this.name = name;
            this.age = age;
            this.yearsOfExperience = yearsOfExperience;
            this.imageResId = imageResId;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getYearsOfExperience() {
            return yearsOfExperience;
        }

        public int getImageResId() {
            return imageResId;
        }
    }
}
