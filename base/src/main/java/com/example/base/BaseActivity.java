package com.example.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.commen.ActivityInstanceManager;
import com.example.commen.NetConnectManager;

public abstract class BaseActivity extends AppCompatActivity implements NetConnectManager.INetConnectListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();

        initData(); //一般在子线程中操作

        ActivityInstanceManager.addActivity(this);

        NetConnectManager.getInstance().registerNetConnectListener(this);
    }

    public boolean isConnected(){
        return NetConnectManager.getInstance().isConnectStatus();
    }

    //让子类提供布局
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();


    /**
     * 网路状态
     */
    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
        NetConnectManager.getInstance().unRegisterNetConnectListener(this);
    }
}
