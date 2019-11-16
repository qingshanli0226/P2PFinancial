package com.example.p2pfinancial;

import android.app.Application;

import com.example.base.P2PCrashHandler;
import com.example.common.NetConnectManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        P2PCrashHandler.getInstance(this).init();//未捕获异常初始化
        NetConnectManager.getInstance().init(this);//初始化网络管理
        CacheManager.getInstance().init(this);
    }
}
