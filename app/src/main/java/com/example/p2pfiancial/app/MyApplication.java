package com.example.p2pfiancial.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.commen.NetConnectManager;
import com.example.p2pfiancial.cache.ACache;
import com.example.p2pfiancial.cache.CacheManager;
import com.example.p2pfiancial.userinfo.UserInfoManager;

public class MyApplication extends Application {
    //在整个应用执行过程中，需要提供的变量
    public static Context context; //需要使用的上下文对象：application实例
    public static Handler handler;//需要使用的handler
    public static Thread mainThread;//提供主线程对象
    public static int mainThreadId;//提供主线程对象的id

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread(); //实例化当前Application的线程即为主线程
        mainThreadId = android.os.Process.myTid(); //获取当前线程的id
        ACache aCache = ACache.get(this);

        //设置未捕获异常的处理器
//        P2PCrashHandler.getInstance(context).init();

        //初始化缓存机制
        CacheManager.getInstance().init(this);
        //初始化网络连接
        NetConnectManager.getInstance().init(this);
        //用户信息管理类
        UserInfoManager.getInstance().init(this, aCache);


        //设置未捕获异常的处理器
//        CrashHandler.getInstance().init();
        //初始化ShareSDK
//        ShareSDK.initSDK(this);
    }
}
