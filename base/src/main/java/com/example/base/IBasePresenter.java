package com.example.base;

public interface IBasePresenter<T> {

    void setBaseUrl(String baseUrl);

    void getData();

    void attachView(IBaseView<T> iBaseView);

    void detachView();

    void getBannerImg();
}
