package com.mcgregor.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView myVideoView;
    private Button btnPlayVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void playVideo(View video) {
        String path = "C:\\Users\\WeVar\\AndroidStudioProjects\\MyApplication\\AudioVideo\\app\\src\\main\\res\\raw\\iphonevideo.mp4";
        Uri u = Uri.parse(path);

    }
}