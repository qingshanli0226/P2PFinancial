package com.example.base;

public interface IBasePresenter<T> {
    void getData();
    //void attachView(IBasePresenter<T> ibaseView);

    void attachView(IBaseView ibaseView);

    void detachView();
}
