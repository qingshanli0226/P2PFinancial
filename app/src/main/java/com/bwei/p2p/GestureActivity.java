package com.bwei.p2p;

import android.widget.Toast;

import com.bwei.base.BaseActivity;

public class GestureActivity extends BaseActivity {


    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
    @Override
    public void onConnected() {
        if (!isConnected()) {
            Toast.makeText(this, "当前网络没有连接", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "当前网络连接正常，获取数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnected() {
        Toast.makeText(this,"当前网络没有连接",Toast.LENGTH_SHORT).show();

    }
}
