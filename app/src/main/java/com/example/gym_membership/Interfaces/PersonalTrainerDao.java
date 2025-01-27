package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.PersonalTrainer;

@Dao
public interface PersonalTrainerDao {
    @Insert
    void insert(PersonalTrainer trainer);

    @Query("SELECT * FROM PersonalTrainer WHERE trainerID = :trainerID")
    PersonalTrainer getTrainerById(int trainerID);
}

