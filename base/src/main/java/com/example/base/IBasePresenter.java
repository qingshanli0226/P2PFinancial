package com.example.base;


public interface IBasePresenter<T> {

    void getData();
    void postData();
    void attachView(IBaseView<T> iBaseView);
    void detchView();

}
