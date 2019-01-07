package com.me.guanpj.binder;

/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
// Declare any non-default types here with import statements

import java.util.List;

public interface IUserManager extends android.os.IInterface {
    static final java.lang.String DESCRIPTOR = "com.longrise.jie.myapplication.IUserManager";

    int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    int TRANSACTION_addUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    int TRANSACTION_getUserList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);

    int add(int a, int b) throws android.os.RemoteException;

    void addUser(User user) throws android.os.RemoteException;

    List<User> getUserList() throws android.os.RemoteException;
}

