package com.android.bindercommuication;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class StudentManagerStub extends Binder implements IStudentManager {
    public StudentManagerStub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code)
        {
            case INTERFACE_TRANSACTION:
            {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_GET_AGE:
            {
                data.enforceInterface(DESCRIPTOR);
                int stuId = data.readInt();
                reply.writeNoException();
                reply.writeInt(getAge(stuId));
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    public static IStudentManager asInterface(IBinder obj) {
        if(obj == null) {
            return null;
        }
        IStudentManager in = (IStudentManager)obj.queryLocalInterface(DESCRIPTOR);
        if(in != null) {
            return in;
        }
        return new StudentManager(obj);
    }
}
