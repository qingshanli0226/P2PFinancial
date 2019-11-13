package com.example.month6;

import android.app.Application;
import android.content.Context;

import com.example.common.diyviews.utils.CrashHandler;

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
//        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance(this));

    }
}
