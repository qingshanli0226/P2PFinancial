package com.example.base;

import android.app.ActivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        initview();
        initdata();
        initListener();


        ActivityInstanceManager.addActivity(this);
    }

    protected abstract void initListener();

    protected abstract void initdata();

    protected abstract void initview();

    protected abstract int getlayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
}
