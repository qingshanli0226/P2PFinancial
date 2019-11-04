package com.example.base;

public interface IBasePresenter<T> {

    void setBaseUrl(String baseUrl);

    void getData();

    void regiseterView(IBaseView<T> iBaseView);

    void detachView();
}
