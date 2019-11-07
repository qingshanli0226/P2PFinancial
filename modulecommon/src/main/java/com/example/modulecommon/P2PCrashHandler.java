package com.example.modulecommon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//捕获全局异常的类
public class P2PCrashHandler implements Thread.UncaughtExceptionHandler{
    //上下文
    private Context mContext;

    //文件的存入地址
    private final String PATH = "/sdcard/exception.txt";

    private static P2PCrashHandler instance =null;


    public P2PCrashHandler(Context mContext) {
        this.mContext = mContext.getApplicationContext();

    }
    //单例模式

    public synchronized static P2PCrashHandler getInstance(Context context){
            if (instance == null)
                instance = new P2PCrashHandler(context);

            return instance;
    }
    //处理异常的方法,应用的程序出现未捕获异常时,会把未捕获的异常通过该函数传过来
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.d("LWThrowable",e.toString());
        new Thread(){
            @Override
            public void run() {
                //prepare()和loop()之间的操作就是在住线程中执行的
                //在安卓系统中,默认情况下,一个线程是不可以调用looper进行消息的处理的,除非是主线程
                Looper.prepare();
                Toast.makeText(mContext, "亲,出现了未捕获的异常!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e1){
            e1.printStackTrace();
        }

        try {
            saveToSDCard(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //保存到手机SD卡中
    private void saveToSDCard(Throwable e) throws Exception {
        PrintStream printStream = new PrintStream(new FileOutputStream(PATH));
        printStream.println(getDateTime("yyyy-MM-dd-HH-mm-ss"));
        e.printStackTrace(printStream);
        printStream.close();

    }

    //根据时间格式返回时间
    @SuppressLint("SimpleDateFormat")
    private String getDateTime(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }
}
