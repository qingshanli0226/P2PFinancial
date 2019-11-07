package com.bwei.p2p;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class MyApplication extends Application {

    public static Context context;//上下文对象
    public static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        handler = new Handler();

    }
}
