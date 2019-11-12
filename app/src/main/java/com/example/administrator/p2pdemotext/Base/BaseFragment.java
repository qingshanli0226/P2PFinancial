package com.example.administrator.p2pdemotext.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.IBaseView;

import java.util.List;

public abstract class BaseFragment<T> extends Fragment implements IBaseView<T> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        initData();
        initTopTitle();

    }

    //初始化数据
    protected void initData(){

    }

    //提供布局
    @LayoutRes
    protected abstract int getLayoutId();

    //初始化View
    protected abstract void initView(View view, Bundle savedInstanceState);

    //初始化标题栏
    protected abstract void initTopTitle();
    @Override
    public void onGetDataSucess(T data) {

    }

    @Override
    public void onGetDataListSucess(List<T> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
