package com.bw.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import butterknife.ButterKnife;
import com.bw.common.ActivityInstanceManager;
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initView());

        ButterKnife.bind(this);

        //将当前的activity添加到ActivityManager中
        new ActivityInstanceManager().addActivity(this);

        initTitle();

        initData();

    }

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int initView();

}
