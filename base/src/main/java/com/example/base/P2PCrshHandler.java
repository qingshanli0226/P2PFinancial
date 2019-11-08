package com.example.base;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.common.ActivityInstanceManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class P2PCrshHandler implements Thread.UncaughtExceptionHandler {
    private static P2PCrshHandler instance;
    private Context context;

    public P2PCrshHandler(Context context) {
        this.context = context;
    }

    public static P2PCrshHandler getInstance(Context applicationContext){
        if (instance==null){

            instance=new P2PCrshHandler(applicationContext);
        }
        return instance;
    }

    @Override
    public void uncaughtException(Thread t,Throwable e) {
        Log.d("SSH",e.toString());
        new Thread(){
            @Override
            public void run() {
                //主线程中执行
                Looper.prepare();
                Toast.makeText(context, "出现了未捕获的异常", Toast.LENGTH_SHORT).show();

                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            PrintStream printStream=new PrintStream(new FileOutputStream("/sdcard/exception.txt"));
            e.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        //关掉所有Activity
        ActivityInstanceManager.finshhAllActivity();
        System.exit(1);
    }
    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
