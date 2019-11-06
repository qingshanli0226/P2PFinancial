package com.example.base;

public interface IBasePresenter<T> {

    void attachView(IBaseView<T> iBaseView);

    void detachView();

    void getAllInest(int requestCode);

    void getBannerImg(int requestCode);
}
