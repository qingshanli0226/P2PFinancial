package com.example.base;

public interface IBasePresenter<T> {
    void ongetHttp();
    //void attachView(IBasePresenter<T> ibaseView);

    void attachView(IBaseView<T> ibaseView);

    void detachView();
}
