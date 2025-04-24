package com.example.task51citube.data.db;

import android.content.Context;
import androidx.room.*;
import com.example.task51citube.data.dao.*;
import com.example.task51citube.data.model.*;

@Database(entities={User.class, Video.class}, version=1, exportSchema=false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract VideoDao videoDao();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase get(Context ctx){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(ctx, AppDatabase.class, "task51citube.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
