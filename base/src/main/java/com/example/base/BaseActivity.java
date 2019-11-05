package com.example.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.commen.ActivityInstanceManager;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();

        initData(); //一般在子线程中操作

        ActivityInstanceManager.addActivity(this);
    }

    //让子类提供布局
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
}
