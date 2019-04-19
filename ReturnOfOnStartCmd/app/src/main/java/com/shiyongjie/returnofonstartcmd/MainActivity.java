package com.shiyongjie.returnofonstartcmd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, BgMusicService.class));
        startService(new Intent(MainActivity.this, ThrowExceptionService.class));
        startService(new Intent(MainActivity.this, BatteryListenerService.class));
        startService(new Intent(MainActivity.this, TimerService.class));
    }
}
