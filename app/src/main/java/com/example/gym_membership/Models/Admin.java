package com.example.gym_membership.Models;

import androidx.room.*;

@Entity(tableName = "Admin")
public class Admin {
    @PrimaryKey(autoGenerate = true)
    public int adminID;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
