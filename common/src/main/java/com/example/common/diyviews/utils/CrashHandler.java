package com.example.common.diyviews.utils;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler;
    private Context context;

    private CrashHandler(Context context) {
        this.context = context;
    }

    public static CrashHandler getInstance(Context applicationContext) {
        if (crashHandler == null) {
            crashHandler = new CrashHandler(applicationContext);
        }
        return crashHandler;
    }
    //
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        //提示错误信息, 手机信息处理
        Log.e("xxxx","异常信息"+e.getMessage());
        new Thread(){
            public void run(){
                Looper.prepare();
                Toast.makeText(context, "应用错误", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //异常退出
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
