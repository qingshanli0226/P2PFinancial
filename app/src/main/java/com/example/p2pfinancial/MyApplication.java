package com.example.p2pfinancial;

import android.app.Application;

import com.example.base.P2PCrashHandler;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        P2PCrashHandler.getInstance(this).init();//未捕获异常初始化
    }
}
