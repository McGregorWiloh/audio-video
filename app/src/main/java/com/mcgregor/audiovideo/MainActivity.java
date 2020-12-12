package com.mcgregor.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    //user interface components
    private VideoView myVideoView;
    private Button btnPlayVideo, btnPlayMusic, btnPauseMusic;
    private MediaController mediaController; //object to control video
    private MediaPlayer mediaPlayer; //object to control audio
    private SeekBar volumeSeekBar, progressSeekBar;
    private AudioManager audioManager;
    private Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);
        btnPauseMusic = findViewById(R.id.btnPauseMusic);
        btnPlayMusic = findViewById(R.id.btnPlayMusic);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        progressSeekBar = findViewById(R.id.progressSeekBar);

        mediaPlayer = MediaPlayer.create(this, R.raw.caltonic);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolumeOfUserDevice = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //get maximum volume of user device
        int currentVolumeOfUserDevice = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //get current volume of user device

        volumeSeekBar.setMax(maxVolumeOfUserDevice); //setting maximum volume value
        volumeSeekBar.setProgress(currentVolumeOfUserDevice); //setting current volume value

        progressSeekBar.setOnSeekBarChangeListener(MainActivity.this); //the main activity will listen since we have implement the onSeek in it
        progressSeekBar.setMax(mediaPlayer.getDuration());

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser) {
                    Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        progressSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                },0,1000);

                return;

            case R.id.btnPauseMusic:

                mediaPlayer.pause();
                timer.cancel();

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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(fromUser) {
            Toast.makeText(this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            progressSeekBar.setProgress(progress);
            mediaPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {



    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}