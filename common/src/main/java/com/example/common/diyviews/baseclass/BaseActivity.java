package com.example.common.diyviews.baseclass;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.diyviews.singleclass.ActivityManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //抽象方法,子类设置布局
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //Activity压入栈
        ActivityManager.getInstance().addActivity(this);
        initData();
        initView();
    }

    protected abstract void initData();
    protected abstract void initView();

    //销毁
    protected void finishActivity(){
        ActivityManager.getInstance().removeTopActivity();
    }
    //退出全部页面
    protected void quitApp(){
        ActivityManager.getInstance().removeAllActivity();
    }
    protected abstract int getLayoutId();
}
