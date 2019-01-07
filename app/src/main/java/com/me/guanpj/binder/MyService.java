package com.me.guanpj.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie on 2016-7-26.
 */
public class MyService extends Service {

    class MyStub extends UserManagerImpl {

        List<User> users = new ArrayList<>();

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "MyServer执行add");
            return 2 * a + 2 * b;
        }

        @Override
        public void addUser(User user) {
            users.add(user);
        }

        @Override
        public List<User> getUserList() {
            return users;
        }
    }

    private MyStub mStub = new MyStub();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "返回Stub");
        return mStub;
    }
}
