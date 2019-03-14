package com.android.bindercommuication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class MainActivity extends Activity {
    IStudentManager mStudentManager;

    ServiceConnection conn =  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mStudentManager = StudentManagerStub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mStudentManager = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        try {
            mStudentManager.getAge(55);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
