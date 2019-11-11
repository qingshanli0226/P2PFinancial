package com.example.base;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private static CrashHandler crashHandler;
    private Context context;

    public CrashHandler(Context context) {
        this.context = context;
    }

    public static CrashHandler getInstance(Context appContext){
           if (crashHandler==null){
               crashHandler=new CrashHandler(appContext);
           }
           return crashHandler;
    }


    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        Log.i("uncaughtException", "uncaughtException: "+throwable.getMessage());
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "应用错误", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
