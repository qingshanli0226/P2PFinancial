package com.example.administrator.p2pdemotext.Appinit;

import android.app.Application;

import com.example.base.P2PCrshHandler;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //未捕获异常
       // P2PCrshHandler.getInstance(this).init();
        Fresco.initialize(this);
    }
}
