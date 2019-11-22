package com.bwei.base;

public interface IBasePresenter<T> {
    void getDate();
    void postData();
    void attachView(IbaseView<T> ibaseView);
//    void addListener(IbaseDataCache ibaseDataCache);
//    void unListener();
    void datachView();
}
