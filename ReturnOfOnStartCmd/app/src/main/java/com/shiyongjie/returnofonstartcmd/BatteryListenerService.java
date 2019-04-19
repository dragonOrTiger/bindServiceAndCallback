package com.shiyongjie.returnofonstartcmd;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

public class BatteryListenerService extends Service {

    private static final String TAG = "BatteryListenerService";
    private BroadcastReceiver batteryChangedReceiver;

    public BatteryListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service: onCreate");
        batteryChangedReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                Log.d(TAG, "Battery Level Remaining: " + level + "%");
            }
        };
        registerReceiver(batteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
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
        unregisterReceiver(batteryChangedReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
