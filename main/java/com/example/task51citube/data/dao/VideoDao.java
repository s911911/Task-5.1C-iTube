package com.example.task51citube.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.task51citube.data.model.Video;
import java.util.List;

@Dao
public interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) void insert(Video v);
    @Query("SELECT * FROM Video ORDER BY rowid DESC") LiveData<List<Video>> all();
}
