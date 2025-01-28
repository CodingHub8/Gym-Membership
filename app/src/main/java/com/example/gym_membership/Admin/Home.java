package com.example.gym_membership.Admin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.Interfaces.PaymentDao;
import com.example.gym_membership.Adapters.PaymentAdapter;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.AdminActivityHomeBinding;

import java.util.List;

public class Home extends AppCompatActivity {
    private AdminActivityHomeBinding binding;
    private PaymentDao paymentDao;
    private PaymentAdapter paymentAdapter;
    private List<Payment> paymentList; // Declare paymentList here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize database and DAO
        DBGymMembership db = DBGymMembership.getInstance(this);
        paymentDao = db.paymentDao();

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.membershipApprovalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentAdapter = new PaymentAdapter(this); // Initialize adapter
        recyclerView.setAdapter(paymentAdapter);

        // Fetch all payments initially
        fetchPayments();

        // Setup search bar functionality
        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Not needed for now
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for now
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString();
                searchPayments(query);
            }
        });
    }

    // Fetch all payments from the database and update the adapter
    private void fetchPayments() {
        new Thread(() -> {
            paymentList = paymentDao.getAllPayments(); // Fetch payments
            Log.d("HomeActivity", "Fetched Payments: " + (paymentList != null ? paymentList.size() : "null"));
            if (paymentList != null && !paymentList.isEmpty()) {
                runOnUiThread(() -> {
                    paymentAdapter.updatePayments(paymentList); // Update RecyclerView
                });
            } else {
                Log.d("HomeActivity", "No payments found in database");
            }
        }).start();
    }

    // Filter payments by username
    private void searchPayments(String query) {
        new Thread(() -> {
            List<Payment> filteredPayments = paymentDao.searchPaymentsByUsername("%" + query + "%"); // Use LIKE operator
            runOnUiThread(() -> {
                paymentAdapter.updatePayments(filteredPayments); // Update the adapter with filtered data
            });
        }).start();
    }
}
