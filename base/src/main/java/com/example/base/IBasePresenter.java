package com.example.base;

import com.example.common.view.MyLoadingPage;

//获取数据,各个页面都可以调用该接口获取数据
public interface IBasePresenter<T> {
    void getData(int requestCode);
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
