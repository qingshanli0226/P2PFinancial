package com.example.p2invest;

import android.app.Application;
import android.content.Context;

import com.example.base.CrashHandler;
import com.example.common.NetConnectManager;
import com.example.p2invest.manager.CacheManager;

public class MyApplication extends Application {
    //在整个应用执行过程中，需要提供的变量
    public static Context context;//需要使用的上下文对象：application实例
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
//        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance(this));
        NetConnectManager.getInstance().init(this);
        CacheManager.getInstance(this).init();
    }
}
