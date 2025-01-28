package com.example.gym_membership.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Interfaces.PersonalTrainerDao;
import com.example.gym_membership.Models.PersonalTrainer;
import com.example.gym_membership.R;
import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.databinding.UserActivityTrainerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChooseTrainer extends AppCompatActivity {
    UserActivityTrainerBinding binding;
    private View selectedTrainerView = null;

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

        // Access PersonalTrainerDao via DBGymMembership
        DBGymMembership db = DBGymMembership.getInstance(this);  // Get instance of your database
        PersonalTrainerDao trainerDao = db.personalTrainerDao(); // Access the PersonalTrainerDao

        // Query the database in the background using Executor
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<PersonalTrainer> trainers = trainerDao.getAllPersonalTrainers();  // Get all trainers

            // Update UI on the main thread
            runOnUiThread(() -> {
                LinearLayout trainerContainer = findViewById(R.id.trainer_container);
                for (PersonalTrainer trainer : trainers) {
                    View trainerView = LayoutInflater.from(this).inflate(R.layout.user_activity_trainer_item, trainerContainer, false);

                    // Set trainer details
                    TextView nameText = trainerView.findViewById(R.id.tv_trainer_name);
                    nameText.setText("Name: " + trainer.getTrainerName());

                    TextView ageText = trainerView.findViewById(R.id.tv_trainer_age);
                    ageText.setText("Age: " + trainer.getTrainerAge());

                    TextView experienceText = trainerView.findViewById(R.id.tv_trainer_experience);
                    experienceText.setText("Experience: " + trainer.getExperience() + " years");

                    // Set an OnClickListener for trainer selection
                    trainerView.setOnClickListener(v -> {
                        // Highlight the selected trainer
                        onTrainerSelected(trainerView, trainer);
                    });

                    // Add to the container
                    trainerContainer.addView(trainerView);
                }
            });
        });
    }

    private void onTrainerSelected(View trainerView, PersonalTrainer trainer) {
        // Reset the previously selected trainer's highlight if it exists
        if (selectedTrainerView != null) {
            selectedTrainerView.setBackgroundColor(getResources().getColor(android.R.color.transparent)); // Reset background
            TextView nameText = selectedTrainerView.findViewById(R.id.tv_trainer_name);
            nameText.setTextColor(getResources().getColor(android.R.color.black)); // Reset text color
        }

        // Highlight the currently selected trainer
        trainerView.setBackgroundColor(getResources().getColor(R.color.selected_item_background)); // Change background color
        TextView nameText = trainerView.findViewById(R.id.tv_trainer_name);
        nameText.setTextColor(getResources().getColor(R.color.selected_item_text_color)); // Change text color

        // Update the selectedTrainerView reference
        selectedTrainerView = trainerView;

        // Display a message (optional)
        Toast.makeText(this, "Selected Trainer: " + trainer.getTrainerName(), Toast.LENGTH_SHORT).show();
    }
}