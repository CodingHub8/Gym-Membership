package com.example.gym_membership.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_membership.Models.Payment;
import com.example.gym_membership.R;
import com.example.gym_membership.User.*;
import com.example.gym_membership.User.PaymentProcess;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private final Context context;
    private final List<CardModel> cardList;

    public CardAdapter(Context context, List<CardModel> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.membership_card_template, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardModel cardModel = cardList.get(position);
        holder.cardTitle.setText(cardModel.getMembershipType());
        holder.cardDescription.setText(cardModel.getDescription());
        holder.cardImage.setImageResource(cardModel.getImageResId());

        // Set the click listener for the button
        holder.btnPurchase.setOnClickListener(v -> {
            // Navigate to another activity or fragment
            SharedPreferences preferences = context.getSharedPreferences("Gym_Membership", MODE_PRIVATE);
            String username = preferences.getString("username", "Guest");  // Default is "Guest"
            boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);  // Default is false

            if(!username.isEmpty() && isLoggedIn){
                Intent intent = new Intent(context, PaymentProcess.class);

                // Pass the membership data to the new activity via intent extras
                intent.putExtra("username", username);
                intent.putExtra("membershipType", cardModel.getMembershipType());
                intent.putExtra("price", cardModel.getPrice());
                intent.putExtra("duration", cardModel.getDuration());

                // Start the new activity
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, Login.class);
                Toast.makeText(context, "You must log-in before making a purchase", Toast.LENGTH_SHORT).show();
                // Start the new activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle, cardDescription;
        ImageView cardImage;
        Button btnPurchase;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardDescription = itemView.findViewById(R.id.card_description);
            cardImage = itemView.findViewById(R.id.card_image);
            btnPurchase = itemView.findViewById(R.id.btnPurchase);
        }
    }
}


