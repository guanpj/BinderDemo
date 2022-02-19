package com.me.guanpj.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie on 2016-7-26.
 */
public class MyService extends Service {

    class MyServiceNative extends IMyServiceImpl {

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
    }

    private MyServiceNative mUserManagerNative = new MyServiceNative();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("gpj", "进程：" + Utils.getProcessName()
                + "，线程：" + Thread.currentThread().getName() + "————Server onBind");
        return mUserManagerNative;
    }
}
