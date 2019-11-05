package com.example.common.diyviews.presenter;

public interface InterPresenter<T> {
    void getData();
    void setDataView(PresenterBaseView<T> presenterBaseView);
    void destoryDataView();
}
