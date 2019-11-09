package com.example.modulebase;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
//activity管理类
public class AppManager {
    //饿汉式单例模式
    private AppManager(){
    }
    private static AppManager appManager;
    public static AppManager getInstance(){
        if (appManager==null)
            appManager = new AppManager();
        return appManager;
    }
    //放activity的集合
    private List<AppCompatActivity> activities = new ArrayList<>();
    //activity的添加
    public void add(AppCompatActivity activity){
        if (activity!=null){
            activities.add(activity);
        }
    }

    //activity的指定删除
    public void remove(AppCompatActivity activity){
        if (activity!=null)
        {
            for (int i = 0; i < activities.size()-1; i++) {
                AppCompatActivity currentActivity = activities.get(i);
                if (currentActivity.getClass().equals(activity.getClass())){
                    currentActivity.finish();//销毁当前的activity
                    activities.remove(i);
                }
            }
        }
    }

    //删除当前的activity
    public void removeCurrrent()
    {
        AppCompatActivity activity = activities.get(activities.size() - 1);
        activity.finish();
        activities.remove(activities.size()-1);
    }

    //删除所有的activity
    public void removeAll(){
        for (int i = 0; i < activities.size() - 1; i++) {
            AppCompatActivity activity = activities.get(i);
            activity.finish();
            activities.remove(activity);
        }
    }
    //返回集合大小
    public int getSize(){return activities.size();}

}
