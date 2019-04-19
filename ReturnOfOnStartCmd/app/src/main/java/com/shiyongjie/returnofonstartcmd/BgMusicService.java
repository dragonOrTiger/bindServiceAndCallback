package com.shiyongjie.returnofonstartcmd;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class BgMusicService extends Service {

    private static final String TAG = "BgMusicService";
    private MediaPlayer mediaPlayer = null;
    private boolean isReady = false;

    public BgMusicService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                stopSelf();
                return false;
            }
        });
        try {
            mediaPlayer.prepare();
            isReady = true;
        }catch (IOException e){
            e.printStackTrace();
            isReady = false;
        }
        if (isReady){
            //mediaPlayer.setVolume(0, 0);
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "   onStartCommand    startId = " + startId);
        Log.d(TAG, "   onStartCommand    intent = " + intent);
        if (isReady && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
            Toast.makeText(this, "start play background music", Toast.LENGTH_LONG).show();
        }
        return START_STICKY;
        //return START_NOT_STICKY;
        //return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            Toast.makeText(this, "stop play background music", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
