package com.example.common.diyviews.presenter;

public interface InterfacePresenter<T> {
    void getData();
    void setDataView(PresenterBaseView<T> presenterBaseView);
    void destoryDataView();
}
