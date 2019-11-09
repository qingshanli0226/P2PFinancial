package com.example.base;

public interface IBasePresenter<T> {
    //注册方法
    void attachView(IBaseView<T> iBaseView);

    //注销方法
    void detachView();

    //投资网络请求方法
    void getAllInest(int requestCode);

    //banner图片请求方法
    void getBannerImg(int requestCode);
}
