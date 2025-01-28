package com.example.gym_membership.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private Context context;
    private List<Payment> paymentList;

    public PaymentAdapter(Context context) {
        this.context = context;
        this.paymentList = new ArrayList<>();
    }

    public void updatePayments(List<Payment> payments) {
        this.paymentList = payments; // Update the internal list
        notifyDataSetChanged();      // Refresh the RecyclerView
    }



    @Override
    public PaymentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        Payment payment = paymentList.get(position);
        holder.username.setText(payment.getUsername());
        holder.paymentAmount.setText(String.valueOf(payment.getPaymentAmount()));

        // Optionally handle button click to approve and update expiration
        holder.approveButton.setOnClickListener(v -> {
            // Handle approval logic, e.g., update payment status or expiration
        });
    }


    @Override
    public int getItemCount() {
        return paymentList.size(); // Return the size of the payment list
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView username, paymentAmount, expirationDateTextView;
        Button approveButton;

        public PaymentViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            paymentAmount = itemView.findViewById(R.id.paymentAmount);
            expirationDateTextView = itemView.findViewById(R.id.expirationDateTextView);
            approveButton = itemView.findViewById(R.id.approveButton);
        }
    }
}
