package com.example.gym_membership.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym_membership.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PageNavigator {
    private final Context context;
    private final BottomNavigationView bottomNav;

    public PageNavigator(Context context, BottomNavigationView bottomNav) {
        this.context = context;
        this.bottomNav = bottomNav;

        bottomNav.setOnItemSelectedListener(item -> {
            Intent intent;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                if (context.getClass().equals(Home.class)) {
                    return false; // Already on the Home activity, no need to re-navigate
                }
                intent = new Intent(context, Home.class);
            } else if (itemId == R.id.nav_trainer) {
                if (context.getClass().equals(ChooseTrainer.class)) {
                    return false; // Already on the Trainer activity
                }
                intent = new Intent(context, ChooseTrainer.class);
            } else if (itemId == R.id.nav_bmi) {
                if (context.getClass().equals(BMIProcess.class)) {
                    return false; // Already on the BMI activity
                }
                intent = new Intent(context, BMIProcess.class);
            } else {
                return false; // Unknown menu item
            }

            // Finish the current activity if it's not the same as the target activity
            if (context instanceof Activity) {
                ((Activity) context).finish();  // Finish the current activity before navigating
            }

            // Start the selected activity
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // Reuse the existing instance if it's already at the top
            context.startActivity(intent);
            return true; // Indicate that the click was handled
        });
    }
}
