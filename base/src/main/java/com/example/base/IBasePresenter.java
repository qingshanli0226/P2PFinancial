package com.example.base;

public interface IBasePresenter<T> {

    void getData();

    void regiseterView(IBaseView<T> iBaseView);

    void detachView();
}
