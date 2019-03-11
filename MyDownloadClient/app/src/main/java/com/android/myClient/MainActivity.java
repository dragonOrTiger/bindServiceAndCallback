package com.android.myClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.myservice.IAidlCallback;
import com.android.myservice.IAidlService;

public class MainActivity extends Activity {

    private static final String TAG = "MyClient";
    private IAidlService mService = null;
    private IAidlCallback.Stub mCallback = new IAidlCallback.Stub() {
        @Override
        public void onDownloadCompleted() throws RemoteException {
            Log.d(TAG, "onDownloadComplete");
            doPrecedureAfterDownload();
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IAidlService.Stub.asInterface(service);
            try {
                mService.registerCallback(mCallback);
            }catch(RemoteException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    private void bindService() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.myservice","com.android.myservice.MyService"));
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        bindService();
        Button downloadButton = this.findViewById(R.id.download);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mService.download();
                }catch(RemoteException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    private void doPrecedureAfterDownload() {
        Log.i(TAG, "doPrecedureAfterDownload");
    }
}
