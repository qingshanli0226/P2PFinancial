package com.example.base;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.common.ActivityInstanceManager;

import java.util.Objects;

public class P2PCrashHandler implements Thread.UncaughtExceptionHandler {

    Context context;
    public static P2PCrashHandler mCrashHandler;

    public P2PCrashHandler(Context context) {
        this.context = context;
    }

    public static P2PCrashHandler getInstance(Context context) {
        if (mCrashHandler == null) {
            mCrashHandler = new P2PCrashHandler(context);
        }
        return mCrashHandler;
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e("####", Objects.requireNonNull(e.getMessage()));
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "出现了未捕获异常!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        ActivityInstanceManager.finishAllActivity();

        System.exit(1);
    }
}
