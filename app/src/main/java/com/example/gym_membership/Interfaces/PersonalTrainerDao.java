package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.PersonalTrainer;

import java.util.List;

@Dao
public interface PersonalTrainerDao {
    @Insert
    void insert(PersonalTrainer trainer);

    @Query("SELECT * FROM PersonalTrainer WHERE trainerID = :trainerID")
    PersonalTrainer getTrainerById(int trainerID);

    @Query("SELECT * FROM PersonalTrainer")
    List<PersonalTrainer> getAllPersonalTrainers();
}
