package com.bw.base;

import android.view.View;
import butterknife.ButterKnife;

public abstract class BaseHolder<T> {

    private View rootView;

    private T data;

    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.bind(this,rootView);
    }

    protected abstract View initView();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        refreshData();
    }

    protected abstract void refreshData();

    public View getRootView() {
        return rootView;
    }
}
