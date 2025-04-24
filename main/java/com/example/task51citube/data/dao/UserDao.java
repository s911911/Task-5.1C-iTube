package com.example.task51citube.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.task51citube.data.model.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT) void insert(User u);
    @Query("SELECT * FROM User WHERE username=:u AND passwordHash=:h LIMIT 1")
    User login(String u, String h);
}