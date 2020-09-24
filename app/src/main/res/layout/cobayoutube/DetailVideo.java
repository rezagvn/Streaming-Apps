package com.example.cobayoutube;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
, YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener
{

    YouTubePlayerView playerview;
    String API_KEY="[YOUR_API]";
    String VIDEO_ID = "";

    public static final String EXTRA_VIDEO = "extra_video";
    private TextView judul, deskirpsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        playerview = (YouTubePlayerView) findViewById(R.id.playerView);
        playerview.initialize(API_KEY,this);

        judul = findViewById(R.id.judulVideo);
        deskirpsi = findViewById(R.id.deskripsiVideo);

        Video video = getIntent().getParcelableExtra(EXTRA_VIDEO);
        VIDEO_ID = video.getVideoID();

        judul.setText(video.getJudulVideo());
        deskirpsi.setText(video.getDeskripsiVideo());


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);
        if(!b)
        {
            youTubePlayer.loadVideo(VIDEO_ID);
        }

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


}
