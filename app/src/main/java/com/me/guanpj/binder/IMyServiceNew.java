package com.me.guanpj.binder;

/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
// Declare any non-default types here with import statements

import java.util.List;


public interface IMyServiceNew extends android.os.IInterface {
    //唯一性标识
    static final java.lang.String DESCRIPTOR = "com.me.guanpj.binder.IMyService";

    //方法标识，用十六进制表示
    int TRANSACTION_addUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    int TRANSACTION_getUserList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    //Server 具有的能力
    void addUser(User user) throws android.os.RemoteException;
    List<User> getUserList() throws android.os.RemoteException;
}

