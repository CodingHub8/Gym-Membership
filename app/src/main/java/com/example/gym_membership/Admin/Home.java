package com.example.gym_membership.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_membership.Adapters.AdminApprovalAdapter;
import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.AdminApprovalModel;
import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.AdminActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    AdminActivityHomeBinding binding;
    Intent intent;
    DBGymMembership db;
    private AdminApprovalAdapter adapter;
    private List<AdminApprovalModel> approvalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = AdminActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = DBGymMembership.getInstance(this); // Access the Room database

        setupRecyclerView();
        loadPendingApprovals();
    }

    private void setupRecyclerView() {
        RecyclerView membershipApprovalListContainer = findViewById(R.id.membershipApprovalList);
        membershipApprovalListContainer.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list and a click listener
        adapter = new AdminApprovalAdapter(this, new ArrayList<>(), item -> {
            // Handle approval logic here
            approveMembership(item);
        });

        membershipApprovalListContainer.setAdapter(adapter);
    }

    private void loadPendingApprovals() {
        // Fetch payment data and map it to AdminApprovalModel
        List<Payment> payments = db.paymentDao().getAllPayments(); // Fetch payment data
        if (payments != null && !payments.isEmpty()) {
            Log.d("PendingApprovals", "Payments fetched: " + payments.size());
        } else {
            Log.d("PendingApprovals", "No payments found.");
        }
        approvalList = new ArrayList<>();

        for (Payment payment : payments) {
            // Map Payment data to AdminApprovalModel
            AdminApprovalModel model = new AdminApprovalModel(
                    payment.getUsername(),
                    "$" + payment.getPaymentAmount(), // Format amount
                    payment.getExpirationDate()

            );
            approvalList.add(model);
        }

        // Update RecyclerView
        AdminApprovalAdapter adapter = new AdminApprovalAdapter(this, approvalList, item -> {
            // Handle approval logic here
        });

        Log.d("ApprovalList", "List size before passing to adapter: " + approvalList.size());
        for (AdminApprovalModel model : approvalList) {
            Log.d("ApprovalList", "Item: " + model.getUsername() + ", " + model.getPaymentAmount());
        }
        // Update the adapter with the approval list
        adapter.updateApprovalList(approvalList);
        Log.d("AdapterState", "Adapter list size: " + adapter.getItemCount());
    }

    private void approveMembership(AdminApprovalModel item) {
        // Example logic for approving a membership
        // This can be customized to update the database or show a confirmation message
        String message = "Approved membership for: " + item.getUsername();
        // Update UI or notify user
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
