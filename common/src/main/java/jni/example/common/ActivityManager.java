package jni.example.common;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ActivityManager {


    //单例模式：饿汉式
    private ActivityManager(){
    }
    private static ActivityManager activityManager = new ActivityManager();

    public static ActivityManager getInstance(){
        return activityManager;
    }

    //提供栈的对象
    private Stack<Activity> activityStack = new Stack<>();

    //activity的添加
    public void add(Activity activity){
        if(activity != null){
            activityStack.add(activity);
        }
    }

    //删除当前的activity
    public void removeCurrent(){
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //删除所有的activity
    public void removeAll(){
        for (int i = activityStack.size() - 1;i >= 0;i--){
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

    //返回栈大小
    public int size(){
        return activityStack.size();
    }

    //为什么使用LinkedList？因为进行频繁增加删除操作
    private static List<Activity> activityList = new LinkedList<>();


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
