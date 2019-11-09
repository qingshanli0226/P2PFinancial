package com.example.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.ActivityInstanceManager;

import java.util.LinkedList;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        //把子activity添加到链表集合中
        ActivityInstanceManager.addActivity(this);
        initView();
        initData();
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
    }
}
