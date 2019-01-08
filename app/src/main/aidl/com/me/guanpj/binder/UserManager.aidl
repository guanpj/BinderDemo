// IUserManager.aidl
package com.me.guanpj.binder;

import com.me.guanpj.binder.User;
// Declare any non-default types here with import statements

interface UserManager {
    void addUser(in User user);

    List<User> getUserList();
}
