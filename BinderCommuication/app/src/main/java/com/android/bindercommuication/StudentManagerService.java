package com.android.bindercommuication;

import android.os.RemoteException;

public class StudentManagerService extends StudentManagerStub {
    @Override
    public int getAge(int studentId) throws RemoteException {
        return Math.abs(studentId % 30);
    }
}
