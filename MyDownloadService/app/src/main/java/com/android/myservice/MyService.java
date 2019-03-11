package com.android.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    private IAidlCallback mCallback = null;
    private IAidlService.Stub mBinder = new IAidlService.Stub() {
        @Override
        public void registerCallback(IAidlCallback cb) throws RemoteException {
            mCallback = cb;
        }

        @Override
        public void download() throws RemoteException {
            download();
        }

        @Override
        public void unregisterCallback() throws RemoteException {
            mCallback = null;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    private void doDownload() throws RemoteException{
        Log.d(TAG, "doDownload");
        Log.d(TAG, "Download Start");
        Log.d(TAG, "Downloading");
        Log.d(TAG, "Download Complete");
        mCallback.onDownloadCompleted();
    }
}
