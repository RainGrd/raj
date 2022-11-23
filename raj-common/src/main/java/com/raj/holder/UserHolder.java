package com.raj.holder;

import com.raj.entity.front.User;

/**
 * @Classname UserHolder
 * @Description 用户持有人
 * @Version 1.0.0
 * @Date 2022/11/22 15:47
 * @Author RainGrd
 */
public class UserHolder {
    /**
     * 注册本地线程
     */
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        threadLocal.set(user);
    }

    public static User getUser() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }
}
