package com.example.gym_membership.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.Admin;
import com.example.gym_membership.databinding.AdminActivityLoginBinding;

public class Login extends AppCompatActivity {
    AdminActivityLoginBinding binding;
    DBGymMembership db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = DBGymMembership.getInstance(this);

        binding.btnLogin.setOnClickListener(view -> {
            String username = binding.username.getText().toString();
            String password = binding.etPassword.getText().toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please fill in all credentials", Toast.LENGTH_SHORT).show();
            } else {
                Admin admin = db.adminDao().authenthicateAdmin(username.trim(), password.trim());
                if(admin == null){
                    Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
                    binding.username.setText("");
                    binding.etPassword.setText("");
                    return;
                }else{
                    Intent intent = new Intent(this, Home.class);
                    intent.putExtra("adminId", admin.adminID);
                    startActivity(intent);
                }
            }

        });
    }

    public void toUserPage(View view){
        Intent intent = new Intent(this, com.example.gym_membership.User.Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Reuse the existing instance if it's already at the top
        startActivity(intent);
        finish();
    }
}
