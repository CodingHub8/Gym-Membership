package com.example.gym_membership.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gym_membership.R;
import com.example.gym_membership.Models.AdminApprovalModel;

import java.util.List;

public class AdminApprovalAdapter extends RecyclerView.Adapter<AdminApprovalAdapter.ViewHolder> {

    private final Context context;
    private final List<AdminApprovalModel> approvalList;
    private final OnApprovalClickListener listener;
    RecyclerView holder;

    public AdminApprovalAdapter(Context context, List<AdminApprovalModel> approvalList, OnApprovalClickListener listener) {
        this.context = context;
        this.approvalList = approvalList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_activity_home_approval_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("AdapterDebug", "Approval list size in adapter: " + approvalList.size());
        AdminApprovalModel item = approvalList.get(position);

        holder.usernameTextView.setText(item.getUsername());
        holder.paymentAmountTextView.setText(item.getPaymentAmount());
        holder.expirationDateTextView.setText(item.getExpirationDate());

        // Handle Approve Button Click
        holder.approveButton.setOnClickListener(v -> listener.onApproveClicked(item));
    }


    @Override
    public int getItemCount() {
        return approvalList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView paymentAmountTextView;
        TextView expirationDateTextView;
        Button approveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            paymentAmountTextView = itemView.findViewById(R.id.paymentAmount);
            expirationDateTextView = itemView.findViewById(R.id.expirationDateTextView);
            approveButton = itemView.findViewById(R.id.approveButton);
        }
    }

    // Method to update the approval list


    public interface OnApprovalClickListener {
        void onApproveClicked(AdminApprovalModel item);
    }
}
