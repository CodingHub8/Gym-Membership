package com.example.gym_membership.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.User;
import com.example.gym_membership.databinding.UserActivityLoginBinding;

public class Login extends AppCompatActivity {
    UserActivityLoginBinding binding;
    Intent intent;
    DBGymMembership db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = DBGymMembership.getInstance(this); // Access the Room database

        intent = getIntent();
        if(intent.hasExtra("username") && intent.hasExtra("password")){
            binding.etUsername.setText(intent.getStringExtra("username"));
            binding.etPassword.setText(intent.getStringExtra("password"));
        }
    }

    public void login(View view){
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in all credentials", Toast.LENGTH_SHORT).show();
        } else {
            User user = db.userDao().getUserAuthentication(username, password);

            if (user != null) {
                Toast.makeText(getApplicationContext(), "Welcome, " + user.username, Toast.LENGTH_SHORT).show();

                // Do something with the data, such as navigating to another screen
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("username", user.username);
                intent.putExtra("phone", user.phone);
                intent.putExtra("email", user.email);
                intent.putExtra("membershipID", user.membershipID);
                intent.putExtra("membershipStatus", user.membershipStatus);

                // Save data in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("Gym_Membership", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", user.username);
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void toSignupPage(View view){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
