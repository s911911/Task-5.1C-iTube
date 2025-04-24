package com.example.task51citube.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.*;
import android.webkit.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.task51citube.R;

public class PlayerFragment extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, ViewGroup c, Bundle b) {
        View v = inf.inflate(R.layout.fragment_player, c, false);

        String videoId = getArguments() != null
                ? getArguments().getString("videoUrl", "")
                : "";

        WebView wv = v.findViewById(R.id.webPlayer);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setMediaPlaybackRequiresUserGesture(false);
        wv.setWebChromeClient(new WebChromeClient());

        String html = "<!DOCTYPE html><html><head><style>"
                + "html,body{margin:0;height:100%;overflow:hidden;}"
                + "#player{position:absolute;top:0;left:0;width:100%;height:100%;}"
                + "</style></head><body>"
                + "<div id=\"player\"></div>"
                + "<script src=\"https://www.youtube.com/iframe_api\"></script>"
                + "<script>"
                + "var player;"
                + "function onYouTubeIframeAPIReady(){"
                + "  player = new YT.Player('player',{"
                + "    videoId:'" + videoId + "',"
                + "    playerVars:{'playsinline':1,'autoplay':1},"
                + "    events:{'onReady':onReady}"
                + "  });"
                + "}"
                + "function onReady(){ player.playVideo(); }"
                + "</script></body></html>";

        wv.loadDataWithBaseURL("https://www.youtube.com", html,
                "text/html", "UTF-8", null);
        return v;
    }
}


