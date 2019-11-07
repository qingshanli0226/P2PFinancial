package com.example.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityInstanceManager {
    private  static List<Activity> activityList=new LinkedList<>();

    public static  void addActivity(Activity instance){
        activityList.add(instance);
    }
    public static  void removeActivity(Activity instance){
        if (activityList.contains(instance)){
            activityList.remove(instance);
        }
    }
    public static void finishAllActivity(){
        for (Activity activity:activityList){
            activity.finish();
        }
    }
}
