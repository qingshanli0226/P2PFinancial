package com.example.modulebase;

public interface IBasePresenter<T> {
    //requestCode来区分不同的网络请求
    void doHttpRequest(int requestCode);
    void doHttpPostRequest(int requestCode);
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
