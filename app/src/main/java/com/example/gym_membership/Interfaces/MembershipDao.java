package com.example.gym_membership.Interfaces;

import androidx.room.*;

import com.example.gym_membership.Models.Membership;

import java.util.*;

@Dao
public interface MembershipDao {
    @Insert
    void insert(Membership... membership);

    @Query("SELECT * FROM Membership WHERE membershipID = :membershipID")
    Membership getMembershipById(int membershipID);

    @Query("SELECT * FROM Membership")
    List<Membership> getAllMemberships();
}

