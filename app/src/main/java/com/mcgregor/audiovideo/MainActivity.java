package com.mcgregor.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView myVideoView;
    private Button btnPlayVideo, btnPlayMusic, btnPauseMusic;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);
        btnPauseMusic = findViewById(R.id.btnPauseMusic);
        btnPlayMusic = findViewById(R.id.btnPlayMusic);

        mediaPlayer = MediaPlayer.create(this, R.raw.caltonic);

        //mediaController = new MediaController(MainActivity.this);

        btnPlayVideo.setOnClickListener(MainActivity.this);
        btnPauseMusic.setOnClickListener(MainActivity.this);
        btnPlayMusic.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View buttonView) {
        switch(buttonView.getId()) {
            case R.id.btnPlayVideo:
               playVideo();
                break;

            case R.id.btnPlayMusic:

                mediaPlayer.start();
                return;

            case R.id.btnPauseMusic:
                mediaPlayer.pause();
                return;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void playVideo() {

        Uri u = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.iphonevideo);
        myVideoView.setVideoURI(u);
        mediaController = new MediaController(this);
        myVideoView.setMediaController(mediaController);
        mediaController.setAnchorView(myVideoView);
        myVideoView.start();
    }
}