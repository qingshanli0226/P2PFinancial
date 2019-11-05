package com.example.common.diyviews.singleclass;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    private static ActivityManager manager=new ActivityManager();
    private Stack<Activity> stack=new Stack<>();  //使用栈管理界面

    private ActivityManager() {
    }
    public static ActivityManager getInstance(){
        return manager;
    }
    //方法依次为: 加 删一个指定 删栈顶(当前) 删全部
    public void addActivity(Activity activity){
        stack.add(activity);
    }
    public void removeActivity(Activity activity){
        if (stack.size()>0){
            for (Activity i:stack){
                if (i.getClass().equals(activity.getClass())){
                    activity.finish();
                    stack.remove(activity);
                    activity=null;
                    break;
                }
            }
        }
    }

    public void removeTopActivity(){
        Activity activity = stack.lastElement();
        removeActivity(activity);
    }

    public void removeAllActivity(){
        while (stack.size()>0){
            removeTopActivity();
        }
    }

    public int getSize(){
        return stack.size();
    }
}
