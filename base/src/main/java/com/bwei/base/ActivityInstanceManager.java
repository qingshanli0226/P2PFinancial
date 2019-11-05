package com.bwei.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityInstanceManager {
    private static List<Activity> activityList = new ArrayList<>();


    //添加Activity
    public static void addActivity(Activity instance) {
        activityList.add(instance);
    }

    //从链表中删除
    public static void removeActivity(Activity instance){
        if (activityList.contains(instance)) {
            activityList.remove(instance);
        }
    }

    //关闭应用中所有的打开activity
    public static void finishAllActivity() {
        for(Activity item:activityList) {
            item.finish();
        }
    }
}
