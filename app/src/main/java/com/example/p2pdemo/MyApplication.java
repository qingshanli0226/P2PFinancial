package com.example.p2pdemo;

import android.app.Application;



import com.example.base.ExpetionCrashHandler;


public class MyApplication extends Application {

//    public static RefWatcher refWatcher;
//    public static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
//        myApplication=this;
//
//        MultiDex.install(this);
//        ExpetionCrashHandler.getInstance(this).init();
//
//        if(!LeakCanary.isInAnalyzerProcess(this)){
//            refWatcher=LeakCanary.install(this);
//        }
    }
}
