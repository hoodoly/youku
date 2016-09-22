package com.app.web.utils;


import com.app.user.module.User;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 16/9/21
 */
public final class UserUtils {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    public UserUtils(){}

    public static User getCurrentUser() {
        return userThreadLocal.get();
    }

    public static void setCurrentUser(User user){
        userThreadLocal.set(user);
    }

    public static void cleanCurrentUser(){
        userThreadLocal.remove();
    }
}
