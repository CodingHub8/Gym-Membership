package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Query("SELECT * FROM User WHERE userID = :id")
    User getUserById(int id);

    @Query("SELECT * FROM User WHERE username = :username COLLATE NOCASE AND password = :password")
    User getUserAuthentication(String username, String password);

    // Get all users
    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    // Delete all users
    @Query("DELETE FROM User")
    void deleteAll();
}