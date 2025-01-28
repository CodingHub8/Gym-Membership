package com.example.gym_membership.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gym_membership.Adapters.CardAdapter;
import com.example.gym_membership.Adapters.CardModel;
import com.example.gym_membership.Adapters.PaymentAdapter;
import com.example.gym_membership.Database.DBGymMembership;
import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.R;
import com.example.gym_membership.databinding.AdminActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    AdminActivityHomeBinding binding;
    Intent intent;
    DBGymMembership db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = AdminActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = DBGymMembership.getInstance(this); // Access the Room database
        displayPendingApprovals();
    }

    private void displayPendingApprovals(){
        PaymentAdapter adapter = new PaymentAdapter(getApplicationContext());
        RecyclerView membershipApprovalListContainer = findViewById(R.id.membershipApprovalList);
        membershipApprovalListContainer.setAdapter(adapter);

        List<Payment> payments = db.paymentDao().getAllPayments(); // Fetch payment data
        adapter.updatePayments(payments); // Update the adapter
    }
}
