package com.shiyongjie.returnofonstartcmd;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ThrowExceptionService extends Service {

    private static final String TAG = "ThrowExceptionService";
    private final int DELAY = 5000;
    private Handler mHandler = new Handler();
    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, DELAY / 1000 + "s after");
            // 故意制造异常，使该进程挂掉
            Integer.parseInt("ok");
        }
    };

    public ThrowExceptionService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        mHandler.postDelayed(mTask, DELAY);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "   onStartCommand    startId = " + startId);
        Log.d(TAG, "   onStartCommand    intent = " + intent);
        return START_STICKY;
        //return START_NOT_STICKY;
        //return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
