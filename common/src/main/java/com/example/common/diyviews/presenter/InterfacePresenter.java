package com.example.common.diyviews.presenter;

public interface InterfacePresenter<T> {
    void getGetData();
    void getPostData();
    void setDataView(PresenterBaseView<T> presenterBaseView);
    void destoryDataView();
}
