package com.example.task51citube.ui;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import com.example.task51citube.R;
import com.example.task51citube.adapter.VideoAdapter;
import com.example.task51citube.data.repository.VideoRepository;

public class PlaylistFragment extends Fragment {

    @Override public View onCreateView(@NonNull LayoutInflater inf,ViewGroup c,Bundle b){
        View v = inf.inflate(R.layout.fragment_playlist,c,false);
        RecyclerView rv = v.findViewById(R.id.rvPlaylist);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoAdapter ad = new VideoAdapter(url->{
            Bundle args=new Bundle(); args.putString("videoUrl",url);
            androidx.navigation.fragment.NavHostFragment.findNavController(this)
                    .navigate(R.id.playerFragment,args);
        });
        rv.setAdapter(ad);
        VideoRepository.get(requireContext()).all()
                .observe(getViewLifecycleOwner(), ad::submitList);
        return v;
    }
}
