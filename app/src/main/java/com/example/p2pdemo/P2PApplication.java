package com.example.p2pdemo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.modulecommon.P2PCrashHandler;

public class P2PApplication extends Application {
    //在整个应用执行过程中，需要提供的变量
    public static Context context;//需要使用的上下文对象：application实例
    public static Handler handler;//需要使用的handler
    public static Thread mainThread;//提供主线程对象
    public static int mainThreadId;//提供主线程对象的id
    @Override
    public void onCreate() {
        super.onCreate();
        P2PCrashHandler.getInstance(this);
        context = this.getApplicationContext();
    }
}
