package com.android.bindercommuication;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

interface IStudentManager extends IInterface {
    String DESCRIPTOR = "com.android.bindercommuication.StudentManagerStub";
    int TRANSACTION_GET_AGE = (IBinder.FIRST_CALL_TRANSACTION + 0);
    int getAge(int studentId) throws RemoteException;
}
