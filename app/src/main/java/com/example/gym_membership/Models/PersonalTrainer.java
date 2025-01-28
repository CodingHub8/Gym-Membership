package com.example.gym_membership.Models;

import androidx.annotation.*;
import androidx.room.*;

@Entity(tableName = "PersonalTrainer",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "userID",
                childColumns = "userID",
                onDelete = ForeignKey.CASCADE))
public class PersonalTrainer {
    @PrimaryKey(autoGenerate = true)
    public int trainerID;

    @ColumnInfo(name = "trainerName")
    public String trainerName;

    @ColumnInfo(name = "trainerAge")
    public int trainerAge;

    @ColumnInfo(name = "experience")
    public double experience; // in years

    @Nullable
    @ColumnInfo(name = "userID")
    public Integer userID;

    public PersonalTrainer(String trainerName, int trainerAge, double experience, @Nullable Integer userID) {
        this.trainerName = trainerName;
        this.trainerAge = trainerAge;
        this.experience = experience;
        this.userID = userID;
    }
}
