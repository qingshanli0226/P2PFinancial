package jni.example.base;

import android.view.View;

public interface IFragment {
    //返回要关联的xml布局ID
    int layoutId();

    int RelativeLayoutID();
    //初始化控件
    void init(View view);

    //初始化数据源
    void initData();
}
