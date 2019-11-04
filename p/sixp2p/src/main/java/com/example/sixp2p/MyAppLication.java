package com.example.sixp2p;

import android.app.Application;
import android.text.style.LeadingMarginSpan;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;

public class MyAppLication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
