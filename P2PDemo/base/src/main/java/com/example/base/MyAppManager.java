package com.example.base;

import android.app.Activity;

import java.util.Stack;

public class MyAppManager {


    private MyAppManager() {
    }
    private static MyAppManager myAppManager=new MyAppManager();
    public static  MyAppManager getInstance(){
        if(myAppManager!=null){
            synchronized (MyAppManager.class){
                if(myAppManager!=null){
                    myAppManager=new MyAppManager();
                }
            }
        }
        return myAppManager;
    }

    private Stack<Activity> activityStack=new Stack<>();

    public void addApp(Activity activity){
        if(activity!=null){
            activityStack.add(activity);
        }
    }



    public void remove(Activity activity){
        if(activity!=null){

            for (int i=activityStack.size()-1;i>=0;i--){
                Activity activityCurrent = activityStack.get(i);
                if(activityCurrent.getClass().equals(activity.getClass())){
                    activityCurrent.finish();
                    activityStack.remove(i);
                }
            }
        }
    }
    public void removeLast(){
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    public void removeAll(){
        for (int i=activityStack.size()-1;i>=0;i--){
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

}
