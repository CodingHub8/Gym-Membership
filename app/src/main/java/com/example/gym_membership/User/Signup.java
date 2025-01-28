package com.example.gym_membership.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.User;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivitySignupBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Signup extends AppCompatActivity {
    UserActivitySignupBinding binding;
    DBGymMembership db;
    ExecutorService executorService; // For performing DB operations off the main thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = DBGymMembership.getInstance(this);
        executorService = Executors.newSingleThreadExecutor(); // Set up executor for background operations
    }

    public void signup(View view) {
        String username = binding.etSignupUsername.getText().toString();
        String password = binding.etSignupPassword.getText().toString();
        String rePassword = binding.etSignupRePassword.getText().toString();
        String email = binding.etSignupEmail.getText().toString();
        String phone = binding.etSignupPhone.getText().toString();

        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(rePassword)) {
            binding.etSignupPassword.setText("");
            binding.etSignupRePassword.setText("");
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        } else {
            // Create a User object for Room
            User newUser = new User(username, email, phone, rePassword, null, "Inactive");

            // Run the database on a background thread
            executorService.execute(() -> {
                try {
                    long result = db.userDao().insert(newUser);

                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

                            // Save data in SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", username);
                            editor.putString("password", rePassword);
                            editor.apply();

                            // Navigate to the Login activity
                            Intent intent = new Intent(this, Login.class);
                            intent.putExtra("username", username);
                            intent.putExtra("password", rePassword);
                            startActivity(intent);
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
        }
    }

    public void toLoginPage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
