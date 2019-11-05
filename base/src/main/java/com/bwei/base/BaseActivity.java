package com.bwei.base;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        manager=new ActivityManager(this,);
        initView();
        initDate();

    }

    protected abstract void initView();

    protected abstract void initDate();

    protected abstract int getLayoutId();
}
