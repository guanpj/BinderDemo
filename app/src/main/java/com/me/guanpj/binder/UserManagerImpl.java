package com.me.guanpj.binder;

import android.os.Binder;
import android.util.Log;

import java.util.List;

public abstract class UserManagerImpl extends Binder implements IUserManager {
    /**
     * Construct the mLocalStub at attach it to the interface.
     */
    public UserManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * 将 IBinder 对象转换成 Binder 本地对象或者代理对象
     */
    public static IUserManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        //查找本地对象
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IUserManager))) {
            Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "返回本地对象");
            return ((IUserManager) iin);
        }
        Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "返回代理对象");
        return new UserManagerImpl.Proxy(obj);
    }

    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_addUser: {
                Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "本地对象执行 addUser");
                data.enforceInterface(DESCRIPTOR);
                User arg0;
                if ((0 != data.readInt())) {
                    arg0 = User.CREATOR.createFromParcel(data);
                } else {
                    arg0 = null;
                }
                this.addUser(arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_getUserList: {
                Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "本地对象执行 getUserList");
                data.enforceInterface(DESCRIPTOR);
                List<User> result = this.getUserList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
            }
            default:
                break;
        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements IUserManager {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public void addUser(User user) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
               if (user != null) {
                   _data.writeInt(1);
                   user.writeToParcel(_data, 0);
               } else {
                   _data.writeInt(0);
               }
                Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "代理对象调用 addUser");
                mRemote.transact(UserManagerImpl.TRANSACTION_addUser, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public List<User> getUserList() throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            List<User> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "代理对象调用 getUserList");
                mRemote.transact(UserManagerImpl.TRANSACTION_getUserList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(User.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }
    }
}
