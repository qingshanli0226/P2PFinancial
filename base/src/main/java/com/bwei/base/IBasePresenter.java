package com.bwei.base;

public interface IBasePresenter<T> {
    void getDate();

    void attachView(IbaseView<T> ibaseView);
    void addListener(IbaseDataCache ibaseDataCache);
    void unListener();
    void datachView();
}
