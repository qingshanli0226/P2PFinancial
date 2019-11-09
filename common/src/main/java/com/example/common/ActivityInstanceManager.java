package com.example.common;

import android.app.Activity;

import java.util.LinkedList;

public class ActivityInstanceManager {

    //声明链表集合 增删快
    private static LinkedList<Activity> activityList = new LinkedList<>();
    //添加activity
    public static void addActivity(Activity instance) {
        activityList.add(instance);
    }
    //删除activity
    public static void removeActivity(Activity instance) {
        if (activityList.contains(instance)) {
            activityList.remove(instance);
        }
    }
    //结束全部activity
    public static void finishAllActivity() {
        for (Activity item : activityList) {
            item.finish();
        }
    }
}
