package com.example.gym_membership.User;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Interfaces.PersonalTrainerDao;
import com.example.gym_membership.Models.PersonalTrainer;
import com.example.gym_membership.R;
import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.databinding.UserActivityTrainerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.*;

public class ChooseTrainer extends AppCompatActivity {
    UserActivityTrainerBinding binding;
    private View selectedTrainerView = null;

    @SuppressLint("SetTextI18n")
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
        DBGymMembership db = DBGymMembership.getInstance(this); // Get instance of your database
        PersonalTrainerDao trainerDao = db.personalTrainerDao(); // Access the PersonalTrainerDao

        SharedPreferences preferences = getSharedPreferences("Gym_Membership", MODE_PRIVATE);
        int userID = preferences.getInt("userId", 0);

        // Query the database in the background using Executor
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Fetch the trainer with matching userID
            PersonalTrainer assignedTrainer = trainerDao.getTrainerByUserID(userID); // Method to fetch trainer by userID

            runOnUiThread(() -> {
                LinearLayout trainerContainer = findViewById(R.id.trainer_container);

                if (assignedTrainer != null) {
                    // Display only the assigned trainer
                    View trainerView = LayoutInflater.from(this).inflate(R.layout.user_activity_trainer_item, trainerContainer, false);

                    // Set trainer details
                    TextView nameText = trainerView.findViewById(R.id.tv_trainer_name);
                    nameText.setText("Name: " + assignedTrainer.trainerName);

                    TextView ageText = trainerView.findViewById(R.id.tv_trainer_age);
                    ageText.setText("Age: " + assignedTrainer.trainerAge);

                    TextView experienceText = trainerView.findViewById(R.id.tv_trainer_experience);
                    experienceText.setText("Experience: " + assignedTrainer.experience + " years");

                    // Disable selection
                    trainerView.setClickable(false);
                    trainerView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

                    // Add to the container
                    trainerContainer.addView(trainerView);

                } else {
                    // Fetch unassigned trainers if no trainer is assigned to the user
                    List<PersonalTrainer> trainers = trainerDao.getUnassignedPersonalTrainers(); // Modify this method to fetch trainers without UserID

                    // If no unassigned trainers are found, show a message
                    if (trainers.isEmpty()) {
                        TextView noTrainersMessage = new TextView(this);
                        noTrainersMessage.setText("There are no trainers available.");
                        trainerContainer.addView(noTrainersMessage);
                    } else {
                        // Display unassigned trainers
                        for (PersonalTrainer trainer : trainers) {
                            View trainerView = LayoutInflater.from(this).inflate(R.layout.user_activity_trainer_item, trainerContainer, false);

                            // Set trainer details
                            TextView nameText = trainerView.findViewById(R.id.tv_trainer_name);
                            nameText.setText("Name: " + trainer.trainerName);

                            TextView ageText = trainerView.findViewById(R.id.tv_trainer_age);
                            ageText.setText("Age: " + trainer.trainerAge);

                            TextView experienceText = trainerView.findViewById(R.id.tv_trainer_experience);
                            experienceText.setText("Experience: " + trainer.experience + " years");

                            // Set an OnClickListener for trainer selection
                            trainerView.setOnClickListener(v -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseTrainer.this);
                                builder.setMessage("Choose " + trainer.trainerName + "?");
                                builder.setTitle("Confirm Selection");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Yes", (dialog, which) -> {
                                    db.personalTrainerDao().updateAssignedUser(userID, trainer.trainerID);
                                    onTrainerSelected(trainerView, trainer);
                                });
                                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            });

                            // Add to the container
                            trainerContainer.addView(trainerView);
                        }
                    }
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
        Toast.makeText(this, "Selected Trainer: " + trainer.trainerName, Toast.LENGTH_SHORT).show();
    }
}