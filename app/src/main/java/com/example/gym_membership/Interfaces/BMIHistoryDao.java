package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.BMIHistory;

@Dao
public interface BMIHistoryDao {
    @Insert
    void insert(BMIHistory bmiHistory);

    @Query("SELECT * FROM BMI_History WHERE bmiID = :bmiID")
    BMIHistory getBMIById(int bmiID);
}

