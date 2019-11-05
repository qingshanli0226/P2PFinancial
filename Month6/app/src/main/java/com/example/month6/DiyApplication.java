package com.example.month6;

import android.app.Application;
import android.content.Context;

public class DiyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
