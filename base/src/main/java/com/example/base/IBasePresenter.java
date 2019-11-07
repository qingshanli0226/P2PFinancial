package com.example.base;


public interface IBasePresenter<T> {

    void getData();
    void attachView(IBaseView<T> iBaseView);

}
