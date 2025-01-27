package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.Admin;

@Dao
public interface AdminDao {
    @Insert
    void insert(Admin admin);

    @Query("SELECT * FROM Admin WHERE adminID = :adminID")
    Admin getAdminById(int adminID);
}
