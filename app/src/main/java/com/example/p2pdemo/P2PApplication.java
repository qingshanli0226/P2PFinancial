package com.example.p2pdemo;

import android.app.Application;

import com.example.modulecommon.P2PCrashHandler;

public class P2PApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        P2PCrashHandler.getInstance(this);
    }
}
