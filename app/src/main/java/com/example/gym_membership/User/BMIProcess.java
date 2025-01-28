package com.example.gym_membership.User;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_membership.Models.BMIHistory;
import com.example.gym_membership.Adapters.BMIAdapter;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.UserActivityBmiBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BMIProcess extends AppCompatActivity {
    private UserActivityBmiBinding binding;
    private BMIAdapter bmiAdapter;
    private List<BMIHistory> bmiHistoryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserActivityBmiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_bmi);
        new PageNavigator(this, bottomNav);

        // Initialize the RecyclerView and Adapter
        bmiHistoryList = new ArrayList<>(); // Initialize the list for BMI records
        bmiAdapter = new BMIAdapter(bmiHistoryList); // Initialize the adapter with the empty list
        RecyclerView recyclerView = findViewById(R.id.recycler_view); // Make sure this matches your layout's RecyclerView ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set the layout manager
        recyclerView.setAdapter(bmiAdapter); // Set the adapter

        // Set up date picker for date input
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

            if(height > 3){//if user inputs in cm
                height /= 100;
            }

            // Calculate BMI
            double bmi = weight / (height * height);
            String bmiResult = String.format("%.2f", bmi);

            // Display the BMI result
            binding.tvBmiResult.setText("Your BMI: " + bmiResult);

            // Add new BMIHistory record to the list
            BMIHistory newRecord = new BMIHistory(0, weight, height, bmi, dateStr, 1); // assuming userID is 1
            bmiHistoryList.add(0, newRecord);  // Add to the top of the list

            // Limit RecyclerView data to only 2 items
            if (bmiHistoryList.size() > 2) {
                bmiHistoryList = bmiHistoryList.subList(0, 2); // Keep only the first 2 items
            }

            // Notify the adapter that the new item is inserted
            bmiAdapter.notifyDataSetChanged();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input format. Please enter numbers only.", Toast.LENGTH_SHORT).show();
        }
    }

}
