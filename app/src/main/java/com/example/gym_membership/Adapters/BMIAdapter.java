package com.example.gym_membership.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_membership.Models.BMIHistory;
import com.example.gym_membership.R;

import java.util.List;

public class BMIAdapter extends RecyclerView.Adapter<BMIAdapter.BMIViewHolder> {

    private final List<BMIHistory> bmiHistoryList;

    public BMIAdapter(List<BMIHistory> bmiHistoryList) {
        this.bmiHistoryList = bmiHistoryList;
    }

    @NonNull
    @Override
    public BMIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_activity_bmi_item, parent, false);
        return new BMIViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BMIViewHolder holder, int position) {
        BMIHistory record = bmiHistoryList.get(position);
        holder.tvDate.setText("Date: " + record.date);
        holder.tvWeight.setText("Weight: " + record.weight + " kg");
        holder.tvHeight.setText("Height: " + record.height + " m");
        holder.tvBmi.setText("BMI: " + String.format("%.2f", record.bmi));
    }

    @Override
    public int getItemCount() {
        return bmiHistoryList.size();
    }

    public static class BMIViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvWeight, tvHeight, tvBmi;

        public BMIViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvWeight = itemView.findViewById(R.id.tv_weight);
            tvHeight = itemView.findViewById(R.id.tv_height);
            tvBmi = itemView.findViewById(R.id.tv_bmi);
        }
    }
}
