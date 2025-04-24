package com.example.task51citube.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.task51citube.R;
import com.example.task51citube.data.model.Video;
import com.example.task51citube.data.repository.VideoRepository;

public class HomeFragment extends Fragment {

    @Override public View onCreateView(@NonNull LayoutInflater inf,ViewGroup c,Bundle b){
        View v = inf.inflate(R.layout.fragment_home,c,false);
        EditText etUrl = v.findViewById(R.id.etUrl);
        //play
        v.findViewById(R.id.btnPlay).setOnClickListener(l->{
            String url = etUrl.getText().toString().trim();
            if(TextUtils.isEmpty(url)){toast("Please enter URL");return;}
            Bundle args=new Bundle(); args.putString("videoUrl",url);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.playerFragment,args);
        });

        //add to playlist
        v.findViewById(R.id.btnAdd).setOnClickListener(l->{
            String url = etUrl.getText().toString().trim();
            if(TextUtils.isEmpty(url)){toast("Please enter URL");return;}
            VideoRepository.EXEC.execute(()->
                    VideoRepository.get(requireContext()).insert(new Video(url)));
            toast("Have added playlist");
        });

        //check playlist
        v.findViewById(R.id.btnPlaylist).setOnClickListener(l->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.playlistFragment));
        return v;
    }
    private void toast(String s){ android.widget.Toast.makeText(getContext(),s,android.widget.Toast.LENGTH_SHORT).show();}
}
