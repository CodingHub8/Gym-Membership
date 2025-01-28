package com.example.gym_membership.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.User;
import com.example.gym_membership.databinding.UserActivityLoginBinding;

import java.util.List;

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

//        List<User> users = db.userDao().getAllUsers();
//        for (User u : users) {
//            Log.d("DBCheck", "User: " + u.username + ", Password: " + u.password);
//        }
    }

    public void login(View view){
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in all credentials", Toast.LENGTH_SHORT).show();
        } else {
            User user = db.userDao().getUserAuthentication(username.trim(), password.trim());

            if (user != null) {
                Toast.makeText(getApplicationContext(), "Welcome, " + user.username, Toast.LENGTH_SHORT).show();

                // Do something with the data, such as navigating to another screen
                Intent intent = new Intent(this, Home.class);

                // Save data in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("Gym_Membership", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("userId", user.userID);
                editor.putString("username", user.username);
                editor.putString("phone", user.phone);
                editor.putString("email", user.email);
                editor.putInt("membershipID", user.membershipID != null ? user.membershipID : 0);
                editor.putString("membershipStatus", user.membershipStatus);
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
