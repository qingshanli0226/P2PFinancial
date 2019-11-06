package com.example.base;

import android.view.View;

public interface IBaseFragment {
    int getLayoutId();

    void initView(View view);

    void initData();
}
