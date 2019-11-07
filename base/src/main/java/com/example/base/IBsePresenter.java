package com.example.base;

public interface IBsePresenter<T> {
    void getData();
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
