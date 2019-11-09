package com.example.modulebase;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseHolder<T> {
    private View rootView;
    private T datas;
    public BaseHolder() {
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.bind(this,rootView);

}

    protected abstract View initView();

    public T getDatas(){return datas;}

    public void setDatas(T datas){
        this.datas = datas;
        refreshData();
    }

    protected abstract void refreshData();

    public View getRootView() {
        return rootView;
    }
}