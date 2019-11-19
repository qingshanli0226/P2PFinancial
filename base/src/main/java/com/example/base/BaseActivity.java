package com.example.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.ActivityInstanceManager;
import com.example.common.NetConnectManager;

public abstract class BaseActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //把子activity添加到链表集合中
        ActivityInstanceManager.addActivity(this);
        initView();
        initData();
//        NetConnectManager.getInstance().registerNetConnectListener(this);
    }

    public void initData() {

    }

    @LayoutRes
    protected abstract int setLayout();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //把子activity从链表集合中删除
        ActivityInstanceManager.removeActivity(this);
//        NetConnectManager.getInstance().unregisterNetConnectListener(this);
    }

//    @Override
//    public void onConnect() {
//
//    }
//
//    @Override
//    public void onDisConnect() {
//
//    }

    public boolean isConnectStatus() {
        return NetConnectManager.getInstance().getConnectStatus();
    }
}
