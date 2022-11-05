package com.me.guanpj.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie on 2016-7-26.
 */
public class UserServer extends Service {

    class MyServiceNative extends Stub {

        List<User> users = new ArrayList<>();

        @Override
        public void addUser(User user) {
            Log.e("gpj", "进程：" + Utils.getProcessName()
                    + "，线程：" + Thread.currentThread().getName() + "————Server 执行 addUser");
            users.add(user);
        }

        @Override
        public List<User> getUserList() {
            Log.e("gpj", "进程：" + Utils.getProcessName()
                    + "，线程：" + Thread.currentThread().getName() + "————Server 执行 getUserList");
            return users;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    }

    private MyServiceNative mMyServiceNative = new MyServiceNative();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("gpj", "进程：" + Utils.getProcessName()
                + "，线程：" + Thread.currentThread().getName() + "————Server onBind");
        return mMyServiceNative;
    }
}
