package com.example.p2invest;

import android.app.Application;

import com.example.base.CrashHandler;
import com.example.common.NetConnectManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance(this));
        NetConnectManager.getInstance().init(this);
    }
}
