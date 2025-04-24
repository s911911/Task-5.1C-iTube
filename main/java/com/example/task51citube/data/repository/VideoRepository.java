package com.example.task51citube.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.task51citube.data.dao.VideoDao;
import com.example.task51citube.data.db.AppDatabase;
import com.example.task51citube.data.model.Video;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoRepository {
    private final VideoDao dao;
    private static volatile VideoRepository INSTANCE;
    public static final ExecutorService EXEC = Executors.newSingleThreadExecutor();

    private VideoRepository(Context ctx){ dao = AppDatabase.get(ctx).videoDao(); }

    public static VideoRepository get(Context ctx){
        if(INSTANCE==null){
            synchronized(VideoRepository.class){
                if(INSTANCE==null) INSTANCE = new VideoRepository(ctx.getApplicationContext());
            }
        }
        return INSTANCE;
    }

    public void insert(Video v){ dao.insert(v); }
    public LiveData<List<Video>> all(){ return dao.all(); }
}
