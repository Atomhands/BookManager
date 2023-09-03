package com.niehao.utils;

import java.util.concurrent.ConcurrentHashMap;

public class LockUtil {

    private final static ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();
    private final static Object PRESENT = new Object();

    // 当前线程持有锁
    public static boolean lock(String key) {
        return locks.putIfAbsent(key, PRESENT) == null;
    }

    // 释放
    public static void unlock(String key) {
        locks.remove(key);
    }


}
