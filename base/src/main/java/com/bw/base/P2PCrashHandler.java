package com.bw.base;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import com.bw.common.ActivityInstanceManager;

public class P2PCrashHandler implements Thread.UncaughtExceptionHandler {

    private static P2PCrashHandler instance;
    private Context context;

    public P2PCrashHandler(Context context) {
        this.context = context;
    }

    public static P2PCrashHandler getInstance(Context applicationContext) {

        if (instance == null){
            instance = new P2PCrashHandler(applicationContext);
        }

        return instance;
    }

    //处理异常的方法，应用程序出现未捕获异常时，会把未捕获的异常通过该函数传递过来
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        new Thread(){
            @Override
            public void run() {
                //在Android系统中，默认情况下，一个线程中是不可以调用Looper进行消息的处理的。除非是主线程
                Looper.prepare();
                Toast.makeText(context, "出现了未捕获的异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //把应用程序关掉，因为无法回到原来的执行上下文
        //把所有的activity finish掉
        new ActivityInstanceManager().finishAllActivity();

        //把应用程序停掉
        System.exit(1);

    }

    public void init(){
        //把当前类设置成未捕获异常默认处理类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
