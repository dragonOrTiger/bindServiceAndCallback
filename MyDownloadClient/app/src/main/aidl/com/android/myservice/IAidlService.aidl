// IAidlService.aidl
package com.android.myservice;
import com.android.myservice.IAidlCallback;

// Declare any non-default types here with import statements

interface IAidlService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void registerCallback(IAidlCallback cb);
    void download();
    void unregisterCallback();
}
