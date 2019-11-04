package com.example.base.util;

import android.app.Activity;

import java.util.LinkedList;

public class ActivityInstanceManager {
    private static LinkedList<Activity> activityList = new LinkedList();

    public static void addActivity(Activity instance){
        activityList.add(instance);
    }

    public static void removeActivity(Activity instance){
        if (activityList.contains(instance)){
            activityList.remove(instance);
        }
    }



}
