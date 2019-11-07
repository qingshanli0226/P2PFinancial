package com.example.common;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityInstanceManager {
    //LinkedList进行频繁的增加删除操作
    private static List<Activity> activityList=new LinkedList<>();

    //添加Activity
    public static void addActivity(Activity instance){
        activityList.add(instance);
    }

    //从链表中删除数据
    public static void removeAcitivty(Activity instance){
        //判断是否有这个数据
        if (activityList.contains(instance)){
            activityList.remove(instance);
        }
    }

    //关闭应用中所有的打开的Activity
    public static void finshhAllActivity(){
        for (Activity item : activityList) {
            item.finish();
        }
    }

}
