package com.example.task51citube.adapter;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;
import com.example.task51citube.R;
import com.example.task51citube.data.model.Video;
import java.util.List;
import java.util.function.Consumer;

public class VideoAdapter extends ListAdapter<Video, VideoAdapter.Holder> {

    private final Consumer<String> click;
    public VideoAdapter(Consumer<String> click){
        super(Video.DIFF);
        this.click = click;
    }

    @NonNull @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup p,int vType){
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_video,p,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h,int pos){
        Video v = getItem(pos);
        h.url.setText(v.url);
        h.itemView.setOnClickListener(l-> click.accept(v.url));
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView url;
        Holder(View v){ super(v); url = v.findViewById(R.id.tvTitle); }
    }
}
