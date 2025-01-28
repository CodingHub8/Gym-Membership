package com.example.gym_membership.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gym_membership.Adapters.AdminApprovalAdapter;
import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.AdminApprovalModel;
import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.Models.User;
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

        approvalList = new ArrayList<>();
        setupRecyclerView();
        loadPendingApprovals();
    }

    private void setupRecyclerView() {
        adapter = new AdminApprovalAdapter(this, approvalList, item -> approveMembership(item));
        binding.membershipApprovalList.setLayoutManager(new LinearLayoutManager(this));
        binding.membershipApprovalList.setAdapter(adapter);
    }

    private void loadPendingApprovals() {
        // Fetch payment data and map it to AdminApprovalModel
        List<Payment> payments = db.paymentDao().getAllPayments(); // Fetch payment data
        if (payments != null && !payments.isEmpty()) {
            Log.d("PendingApprovals", "Payments fetched: " + payments.size());
        } else {
            Log.d("PendingApprovals", "No payments found.");
        }

        approvalList.clear(); // Clear the current list to avoid duplication
        for (Payment payment : payments) {
            // Map Payment data to AdminApprovalModel
            AdminApprovalModel model = new AdminApprovalModel(
                    payment.getUsername(),
                    "$" + payment.getPaymentAmount(), // Format amount
                    payment.getExpirationDate()
            );
            approvalList.add(model);
        }

        Log.d("ApprovalList", "List size before passing to adapter: " + approvalList.size());
        for (AdminApprovalModel model : approvalList) {
            Log.d("ApprovalList", "Item: " + model.getUsername() + ", " + model.getPaymentAmount());
        }

        // Notify adapter of data changes
        runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

    private void approveMembership(AdminApprovalModel item) {
        String name = item.getUsername();
        User user = db.userDao().getUserByName(name);
        // Update the user's membership status in the database
        db.userDao().updateMembershipStatusByUserId(user.userID, "Active");

        // Remove the item from the approval list
        approvalList.remove(item);

        // Notify the adapter of the item removal
        runOnUiThread(() -> adapter.notifyDataSetChanged());

        // Show confirmation to the user
        String message = "Approved membership for: " + item.getUsername();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
