package com.example.p2pdemo;

import android.app.Application;


import androidx.multidex.MultiDex;

import com.example.base.ExpetionCrashHandler;
import com.example.common.NetConnectManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


public class MyApplication extends Application {

    public static RefWatcher refWatcher;
    public static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        MultiDex.install(this);
//        ExpetionCrashHandler.getInstance(this).init();
        NetConnectManager.getInstance().init(this);

        if(!LeakCanary.isInAnalyzerProcess(this)){
            refWatcher=LeakCanary.install(this);
        }
    }
}
