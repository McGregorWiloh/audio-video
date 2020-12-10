package com.mcgregor.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView myVideoView;
    private Button btnPlayVideo;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);
        //mediaController = new MediaController(MainActivity.this);

        btnPlayVideo.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View buttonView) {
        switch(buttonView.getId()) {
            case R.id.btnPlayVideo:
               playVideo();
                break;

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