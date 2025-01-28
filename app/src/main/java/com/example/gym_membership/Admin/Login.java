package com.example.gym_membership.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.databinding.AdminActivityLoginBinding;

public class Login extends AppCompatActivity {
    AdminActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, com.example.gym_membership.Admin.Home.class);
            startActivity(intent);
        });
    }

    public void toUserPage(View view){
        Intent intent = new Intent(this, com.example.gym_membership.User.Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Reuse the existing instance if it's already at the top
        startActivity(intent);
        finish();
    }
}
