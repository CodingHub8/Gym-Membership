package com.example.gym_membership.Models;

import androidx.annotation.Nullable;
import androidx.room.*;

@Entity(tableName = "User",
foreignKeys = @ForeignKey(entity = Membership.class, parentColumns = "membershipID", childColumns = "membershipID"))
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userID;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "phone")
    public String phone;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "membershipID")
    @Nullable
    public Integer membershipID;
    @ColumnInfo(name = "membershipStatus")
    public String membershipStatus;

    public User(String username, String email, String phone, String password, @Nullable Integer membershipID, String membershipStatus) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.membershipID = membershipID;
        this.membershipStatus = membershipStatus;
    }
}
