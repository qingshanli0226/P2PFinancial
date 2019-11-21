package com.example.administrator.p2pdemotext.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.base.IBaseView;
import com.example.common.ActivityInstanceManager;

import java.util.List;

import crazyjone.loadinglibrary.View.LoadingStateWidget;

public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseView<T> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        ActivityInstanceManager.addActivity(this);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void onShow(int code) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeAcitivty(this);
    }

    @Override
    public void onHttpRequestDataFailed(int requestCode) {

    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, T data) {


    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<T> data) {

    }
}


