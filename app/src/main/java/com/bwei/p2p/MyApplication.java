package com.bwei.p2p;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.bwei.common.NetcomentManager;

public class MyApplication extends Application {

    public static Context context;//上下文对象
    public static Handler handler;
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        context = this.getApplicationContext();
        handler = new Handler();
//        ErrorUitl.getInstance(context).init();
        NetcomentManager.getInstance(context).init();

    }
}
