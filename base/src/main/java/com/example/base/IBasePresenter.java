package com.example.base;


public interface IBasePresenter<T> {

    void getData();
    void getInvestData();
    void attachView(IBaseView<T> iBaseView);
    void detchView();

}
