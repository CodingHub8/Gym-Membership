package com.example.gym_membership.Models;

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

    @ColumnInfo(name = "userID")
    public int userID;

    public PersonalTrainer(int trainerID, String trainerName, int trainerAge, double experience, int userID) {
        this.trainerID = trainerID;
        this.trainerName = trainerName;
        this.trainerAge = trainerAge;
        this.experience = experience;
        this.userID = userID;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public int getTrainerAge() {
        return trainerAge;
    }

    public void setTrainerAge(int trainerAge) {
        this.trainerAge = trainerAge;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
