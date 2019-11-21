package com.example.base;

public interface IBasePresenter<T> {
    void ongetHttp();
    //void attachView(IBasePresenter<T> ibaseView);

    //POST的网络请求
    void doHttpPostRequest(int requestCode);

    void attachView(IBaseView<T> ibaseView);

    void detachView();
}
