package com.example.common.diyviews.baseclass;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.diyviews.singleclass.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //抽象方法,子类设置布局
        setContentView(getLayoutId());
        //Activity压入栈
        ActivityManager.getInstance().addActivity(this);
        initData();
    }

    protected abstract void initData();

    //销毁
    public void removeActivity(){
        ActivityManager.getInstance().removeTopActivity();
    }
    //退出全部页面
    public void toDeskTop(){
        ActivityManager.getInstance().removeAllActivity();
    }
    protected abstract int getLayoutId();
}
