package com.example.base;


public interface IBasePresenter<T> {

    void getData();
    void postDatas();
    void attachView(IBaseView<T> iBaseView);
    void detchView();

}
