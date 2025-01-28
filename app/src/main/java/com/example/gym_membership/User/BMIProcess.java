package com.example.gym_membership.User;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityBmiBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class BMIProcess extends AppCompatActivity {
    private UserActivityBmiBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserActivityBmiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_bmi);
        new PageNavigator(this, bottomNav);

        // Set up date picker
        binding.etDate.setOnClickListener(v -> showDatePickerDialog());

        // Set up BMI calculation button
        binding.btnCalculateBmi.setOnClickListener(v -> calculateBMI());
    }

    private void showDatePickerDialog() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Format and set the selected date in the EditText
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    binding.etDate.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void calculateBMI() {
        // Get user input
        String weightStr = binding.etWeight.getText().toString();
        String heightStr = binding.etHeight.getText().toString();
        String dateStr = binding.etDate.getText().toString();

        // Validate input
        if (TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(dateStr)) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse weight and height
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            if (weight <= 0 || height <= 0) {
                Toast.makeText(this, "Please enter valid weight and height values.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate BMI
            double bmi = weight / (height * height);
            String bmiResult = String.format("%.2f", bmi);

            // Display the BMI result
            binding.tvBmiResult.setText("Your BMI: " + bmiResult);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input format. Please enter numbers only.", Toast.LENGTH_SHORT).show();
        }
    }
}
