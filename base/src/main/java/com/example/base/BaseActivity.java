package com.example.base;

import android.app.ActivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.NetConnectManager;

public abstract class BaseActivity extends AppCompatActivity implements NetConnectManager.INetListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        initview();
        initdata();
        initListener();


        ActivityInstanceManager.addActivity(this);
        NetConnectManager.getInstance().registINetListener(this);
    }

    protected abstract void initListener();

    protected abstract void initdata();

    protected abstract void initview();

    protected abstract int getlayout();

    public boolean isConnected(){
        return  NetConnectManager.getInstance().isConnectStatus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
        NetConnectManager.getInstance().unregistINetListener(this);
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
