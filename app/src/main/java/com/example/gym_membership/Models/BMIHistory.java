package com.example.gym_membership.Models;

import androidx.room.*;

@Entity(tableName = "BMI_History",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "userID",
                childColumns = "userID",
                onDelete = ForeignKey.CASCADE))
public class BMIHistory {
    @PrimaryKey(autoGenerate = true)
    public int bmiID;

    @ColumnInfo(name = "weight")
    public double weight;

    @ColumnInfo(name = "height")
    public double height;

    @ColumnInfo(name = "bmi")
    public double bmi;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "userID")
    public int userID;


    public BMIHistory(double weight, double height, double bmi, String date, int userID) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = date;
        this.userID = userID;
    }
}
