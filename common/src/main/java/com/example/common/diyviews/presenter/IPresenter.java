package com.example.common.diyviews.presenter;

public interface IPresenter<T> {
    void sendGetRequest();
    void sendPostRequest();
    void setIBaseView(IBaseView<T> presenterBaseView);
    void destoryIBaseView();
}
