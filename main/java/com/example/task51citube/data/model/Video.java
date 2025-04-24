package com.example.task51citube.data.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Video {
    @PrimaryKey @NonNull public String url;

    public Video(){}

    @Ignore public Video(@NonNull String url){ this.url=url; }

    public static final DiffUtil.ItemCallback<Video> DIFF=new DiffUtil.ItemCallback<>(){
        @Override public boolean areItemsTheSame(@NonNull Video o,@NonNull Video n){return o.url.equals(n.url);}
        @Override public boolean areContentsTheSame(@NonNull Video o,@NonNull Video n){return o.url.equals(n.url);}
    };
}
