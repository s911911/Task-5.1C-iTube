package com.example.task51citube.ui;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.webkit.*;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.task51citube.R;

public class PlayerFragment extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_player, container, false);

        String raw = getArguments() != null ? getArguments().getString("youtube_link", "") : "";
        YouTubeInfo info = parseYouTube(raw);
        if (info == null) {
            toast("Unsupported or empty link");
            return v;
        }

        String html = buildHtml(info);

        WebView wv = v.findViewById(R.id.webPlayer);
        WebSettings s = wv.getSettings();
        s.setJavaScriptEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        wv.setWebChromeClient(new WebChromeClient());

        wv.loadDataWithBaseURL(
                "https://www.youtube.com", html, "text/html", "utf-8", null);

        return v;
    }


    private static class YouTubeInfo {
        final String videoId;
        final String listId;
        YouTubeInfo(String videoId, String listId) {
            this.videoId = videoId;
            this.listId  = listId;
        }
    }


    private YouTubeInfo parseYouTube(String url) {
        if (url == null || url.isEmpty()) return null;

        if (!url.startsWith("http")) return new YouTubeInfo(url, null);

        Uri uri = Uri.parse(url);
        String host = uri.getHost();
        if (host == null) return null;

        if (host.contains("youtu.be")) {
            return new YouTubeInfo(uri.getLastPathSegment(), null);
        }

        if (host.contains("youtube.com")) {
            String list = uri.getQueryParameter("list");
            String id   = uri.getQueryParameter("v");

            if (url.contains("/shorts/")) {
                id = uri.getLastPathSegment();
            }
            if (list != null && (id == null || id.isEmpty())) {
                return new YouTubeInfo(null, list);
            }
            if (id != null && !id.isEmpty()) {
                return new YouTubeInfo(id, list);
            }
        }
        return null;
    }
    
    private String buildHtml(YouTubeInfo info) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html><html><head><style>")
                .append("html,body{margin:0;height:100%;overflow:hidden;}#player{position:absolute;top:0;left:0;width:100%;height:100%;}")
                .append("</style></head><body>")
                .append("<div id='player'></div>")
                .append("<script src='https://www.youtube.com/iframe_api'></script>")
                .append("<script>")
                .append("function onYouTubeIframeAPIReady(){ new YT.Player('player',{");

        if (info.listId != null) {
            sb.append("listType:'playlist', list:'").append(info.listId).append("',");
        } else {
            sb.append("videoId:'").append(info.videoId).append("',");
        }
        sb.append("playerVars:{autoplay:1, playsinline:1}});}")
                .append("</script></body></html>");
        return sb.toString();
    }

    private void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}


