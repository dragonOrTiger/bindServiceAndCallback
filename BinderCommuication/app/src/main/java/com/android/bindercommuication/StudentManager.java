package com.android.bindercommuication;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class StudentManager implements IStudentManager {
    IBinder mRemote;

    public StudentManager(IBinder mRemote) {
        this.mRemote = mRemote;
    }

    @Override
    public int getAge(int studentId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeInt(studentId);
            mRemote.transact(TRANSACTION_GET_AGE, data, reply, 0);
            reply.readException();
            int age = reply.readInt();
            return age;
        }finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
