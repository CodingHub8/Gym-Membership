package com.example.gym_membership.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gym_membership.Models.*;
import com.example.gym_membership.Interfaces.*;

import java.util.concurrent.Executors;

@Database(entities = {Admin.class, Membership.class, User.class, PersonalTrainer.class, Payment.class, BMIHistory.class},
        version = 3)
public abstract class DBGymMembership extends RoomDatabase {
    private static DBGymMembership instance;

    public static synchronized DBGymMembership getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DBGymMembership.class, "db_gym_membership")
                    .allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .addCallback(prepopulateCallback) // Add callback for pre-population
                    .build();
        }
        return instance;
    }

    public abstract AdminDao adminDao();
    public abstract MembershipDao membershipDao();
    private static final RoomDatabase.Callback prepopulateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insert default memberships
            Executors.newSingleThreadExecutor().execute(() -> {
                DBGymMembership database = instance;
                if (database != null) {
                    database.membershipDao().insert(
                            new Membership("Bronze", 28.00, 30),
                            new Membership("Silver", 50.00, 60),
                            new Membership("Gold", 72.00, 90)
                    );

                    database.adminDao().insert(
                            new Admin("Admin1", "123"),
                            new Admin("Admin2", "123")
                    );
                }
            });
        }
    };
    public abstract UserDao userDao();
    public abstract PersonalTrainerDao personalTrainerDao();
    public abstract PaymentDao paymentDao();
    public abstract BMIHistoryDao bmiHistoryDao();
}