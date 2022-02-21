package com.me.guanpj.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBind;
    Button btnAddUser;
    Button btnGetSize;
    TextView tvResult;
    IMyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBind = (Button) findViewById(R.id.btn_bind);
        btnAddUser = (Button) findViewById(R.id.btn_add_user);
        btnGetSize = (Button) findViewById(R.id.btn_get_size);

        btnBind.setOnClickListener(this);
        btnAddUser.setOnClickListener(this);
        btnGetSize.setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.txt_result);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConn);
        super.onDestroy();
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("gpj", "进程：" + Utils.getProcessName()
                    + "，线程：" + Thread.currentThread().getName() + "————Client onServiceConnected");
            myService = Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                bindService();
                break;
            case R.id.btn_add_user:
                if (null != myService) {
                    try {
                        Log.e("gpj", "进程：" + Utils.getProcessName()
                                + "，线程：" + Thread.currentThread().getName() + "————Client 调用 addUser");
                        myService.addUser(new User(111, "gpj"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ClientActivity.this, "先绑定 Service 才能调用方法", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_get_size:
                if (null != myService) {
                    try {
                        Log.e("gpj", "进程：" + Utils.getProcessName()
                                +  "，线程：" + Thread.currentThread().getName() + "————Client 调用 getUserList");
                        List<User> userList = myService.getUserList();
                        tvResult.setText("getUserList size:" + userList.size());
                        Log.e("gpj", "进程：" + Utils.getProcessName()
                                +  "，线程：" + Thread.currentThread().getName() + "————UserList Size：" + userList.size());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ClientActivity.this, "先绑定 Service 才能调用方法", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.me.guanpj.binder");
        intent.setComponent(new ComponentName("com.me.guanpj.binder", "com.me.guanpj.binder.UserServer"));

        Log.e("gpj", "进程：" + Utils.getProcessName()
                + "，线程：" + Thread.currentThread().getName() + "————开始绑定 Servcie");
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }
}
