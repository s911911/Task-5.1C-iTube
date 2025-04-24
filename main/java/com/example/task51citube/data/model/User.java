package com.example.task51citube.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey @NonNull public String username;
    public String fullName;
    public String passwordHash;

    public User(){}

    @Ignore
    public User(@NonNull String username,String fullName,String hash){
        this.username=username; this.fullName=fullName; this.passwordHash=hash;
    }
}
