package com.example.task51citube.data.repository;

import android.content.Context;
import com.example.task51citube.data.dao.UserDao;
import com.example.task51citube.data.db.AppDatabase;
import com.example.task51citube.data.model.User;
import com.example.task51citube.util.HashUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private final UserDao dao;
    private static volatile UserRepository INSTANCE;
    public static final ExecutorService EXEC = Executors.newSingleThreadExecutor();

    private UserRepository(Context ctx){ dao = AppDatabase.get(ctx).userDao(); }

    public static UserRepository get(Context ctx){
        if(INSTANCE==null){
            synchronized (UserRepository.class){
                if(INSTANCE==null) INSTANCE = new UserRepository(ctx.getApplicationContext());
            }
        }
        return INSTANCE;
    }

    public boolean register(String user,String name,String pwd){
        try{
            dao.insert(new User(user,name, HashUtil.md5(pwd)));
            return true;
        }catch(Exception e){ return false; }
    }

    public boolean login(String user,String pwd){
        return dao.login(user, HashUtil.md5(pwd)) != null;
    }
}
