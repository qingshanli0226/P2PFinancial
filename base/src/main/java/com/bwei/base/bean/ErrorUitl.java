package com.bwei.base.bean;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;



public class ErrorUitl implements Thread.UncaughtExceptionHandler {
    private static ErrorUitl instance;
    private static Context context;

    private ErrorUitl(Context context) {
        this.context=context;
    }

    public static ErrorUitl getInstance(Context applicationContext) {
        if (instance==null){
            instance=new ErrorUitl(applicationContext);
        }
        return instance;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e("ssssx", "异常消息"+e.getMessage() );
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context,"出现为捕获的异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
