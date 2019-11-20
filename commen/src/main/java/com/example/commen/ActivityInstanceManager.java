package com.example.commen;

import android.app.Activity;

import java.util.LinkedList;

public class ActivityInstanceManager {
    //为什么使用LinkedList？因为进行频繁增加删除操作
    private static LinkedList<Activity> activityList = new LinkedList();

    //添加Activity
    public static void addActivity(Activity instance) {

        activityList.add(instance);
    }

    //从链表中删除
    public static void removeActivity(Activity instance) {
        if (activityList.contains(instance)) {
            instance.finish();
            activityList.remove(instance);
        }
    }

    //关闭应用中所有的打开activity
    public static void finishAllActivity() {
        for (Activity item : activityList) {
            item.finish();
        }
    }
}
