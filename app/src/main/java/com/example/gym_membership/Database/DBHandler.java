//package com.example.gym_membership.Database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import java.util.Map;
//import java.util.HashMap;
//
//public class DBHandler extends SQLiteOpenHelper {
//    public DBHandler(Context context) {
//        super(context, "db_gym_membership", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(
//                "CREATE TABLE Admin (" +
//                        "adminID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "username VARCHAR(255), " +
//                        "password VARCHAR(255)" +
//                ");"
//        );
//
//        db.execSQL(
//                "CREATE TABLE Membership (" +
//                        "membershipID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "membershipType TEXT NOT NULL CHECK (membershipType IN ('Bronze', 'Silver', 'Gold')), " +
//                        "price DECIMAL(5, 2), " +
//                        "duration INT" +
//                ");"
//        );
//
//        db.execSQL(
//                "CREATE TABLE User (" +
//                        "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "name VARCHAR(255), " +
//                        "username VARCHAR(100) NOT NULL, " +
//                        "email VARCHAR(50), " +
//                        "phone VARCHAR(15), " +
//                        "password VARCHAR(255) NOT NULL, " +
//                        "membershipID INTEGER, " +
//                        "membershipStatus TEXT NOT NULL DEFAULT 'Inactive' CHECK (membershipStatus IN ('Inactive', 'Pending', 'Active')), " +
//                        "FOREIGN KEY (membershipID) REFERENCES membership(membershipID)" +
//                ");"
//        );
//
//        db.execSQL(
//                "CREATE TABLE PersonalTrainer (" +
//                        "trainerID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "trainerName VARCHAR(255), " +
//                        "trainerAge INTEGER, " +
//                        "experience DECIMAL(3, 1), " +
//                        "userID INTEGER, " +
//                        "FOREIGN KEY (userID) REFERENCES User(userID)" +
//                ");"
//        );
//
//        db.execSQL(
//                "CREATE TABLE Payment (" +
//                        "paymentID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "username VARCHAR(255) NOT NULL, " +
//                        "paymentAmount REAL NOT NULL DEFAULT 0.00, " +
//                        "expirationDate TEXT NOT NULL CHECK (expirationDate LIKE '__/__/____'), " +
//                        "userID INTEGER, " +
//                        "FOREIGN KEY (userID) REFERENCES User(userID)" +
//                ")"
//        );
//
//        db.execSQL(
//                "CREATE TABLE BMI_History (" +
//                        "bmiID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "weight DECIMAL(5, 2), " +
//                        "height DECIMAL(5, 2), " +
//                        "bmi DECIMAL(4, 2), " +
//                        "date TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP CHECK (date LIKE '__/__/____'), " +
//                        "userID INTEGER, " +
//                        "FOREIGN KEY (userID) REFERENCES User(userID)" +
//                        ")"
//        );
//
//        insertDefaultMembershipTypes(db);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS Admin;");
//        db.execSQL("DROP TABLE IF EXISTS Membership;");
//        db.execSQL("DROP TABLE IF EXISTS User;");
//        db.execSQL("DROP TABLE IF EXISTS PersonalTrainer;");
//        db.execSQL("DROP TABLE IF EXISTS Payment;");
//        db.execSQL("DROP TABLE IF EXISTS BMI_History;");
//        onCreate(db);
//    }
//
//    private void insertDefaultMembershipTypes(SQLiteDatabase db) {
//        Map<String, String> membershipValues = new HashMap<>();
//        membershipValues.put("membershipType", "Bronze");
//        membershipValues.put("price", "28.00");
//        membershipValues.put("duration", "30");//days
//        insert(db, "membership", membershipValues);
//
//        membershipValues.put("membershipType", "Gold");
//        membershipValues.put("price", "50.00");
//        membershipValues.put("duration", "60");//days
//        insert(db, "membership", membershipValues);
//
//        membershipValues.put("membershipType", "Gold");
//        membershipValues.put("price", "72.00");
//        membershipValues.put("duration", "90");//days
//        insert(db, "membership", membershipValues);
//    }
//
//    // Function to insert a single member type
//    public int insert(SQLiteDatabase db, String tableName, Map<String, String> columnValues) {
//        // Validate inputs
//        if (tableName == null || tableName.isEmpty()) {
//            throw new IllegalArgumentException("Table name cannot be null or empty");
//        }
//        if (columnValues == null || columnValues.isEmpty()) {
//            throw new IllegalArgumentException("Column values cannot be null or empty");
//        }
//
//        // Prepare ContentValues from the provided map
//        ContentValues values = new ContentValues();
//        for (Map.Entry<String, String> entry : columnValues.entrySet()) {
//            values.put(entry.getKey(), entry.getValue());
//        }
//
//        // Insert into the specified table
//        long result = db.insert(tableName, null, values);
//        if (result == -1) {
//            db.close();
//            throw new RuntimeException("Failed to insert row into table: " + tableName);
//        }
//
//        return (int) result;
//    }
//}
