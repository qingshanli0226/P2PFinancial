package com.example.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.example.common.MyAppManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ExpetionCrashHandler implements Thread.UncaughtExceptionHandler {
    private static ExpetionCrashHandler expetionCrashHandler;
    private Context context;
    public ExpetionCrashHandler(Context context) {
        this.context=context;
    }
    public static ExpetionCrashHandler getInstance(Context context){
        if(expetionCrashHandler==null){
            synchronized (ExpetionCrashHandler.class){
                if(expetionCrashHandler==null){
                    expetionCrashHandler=new ExpetionCrashHandler(context);
                }
            }
        }
        return expetionCrashHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(context, "未捕获的异常出现了!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(2000);
        }catch (InterruptedException el){
            el.printStackTrace();
        }


        try {
            PrintStream printStream = new PrintStream(new FileOutputStream("/sdcard/exception.txt"));
            e.printStackTrace(printStream);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }


        MyAppManager.getInstance().removeAll();
        System.exit(1);
    }
    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


}
