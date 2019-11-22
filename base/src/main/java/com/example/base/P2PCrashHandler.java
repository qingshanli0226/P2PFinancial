package com.example.base;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.base.manager.ActivityInstanceManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//未捕获异常处理
// 安卓给应用分配了200m 内存
public class P2PCrashHandler implements Thread.UncaughtExceptionHandler {
    private static P2PCrashHandler instance = null;
    private Context context = null; //上下文
    public boolean openUpload = false;
    private static final String LOG_FILE_DIR = "log"; //log文件路径
    private static final String FILE_NAME = ".txt"; //log文件后缀名


    private P2PCrashHandler(Context context) {
        this.context = context;
    }

    public static P2PCrashHandler getInstance(Context context) {
        if (instance == null) {
            instance = new P2PCrashHandler(context);
        }
        return instance;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.i("11111", "uncaughtException: " + e.toString());
        //toast
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "亲,出现了未捕获异常, 休息一下, 马上回来.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //将未捕获信息存入本地
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream("/sdcard/" + LOG_FILE_DIR + FILE_NAME));
            e.printStackTrace(writer);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        ActivityInstanceManager.finishAllActivity();
        System.exit(1);
    }

    //初始化
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
