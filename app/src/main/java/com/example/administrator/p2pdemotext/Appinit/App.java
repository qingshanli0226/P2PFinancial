package com.example.administrator.p2pdemotext.Appinit;

import android.app.Application;

import com.example.base.P2PCrshHandler;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        P2PCrshHandler.getInstance(this).init();
    }
}
