package com.example.base;

import java.util.HashMap;

public interface IBsePresenter<T> {
    void getData();
    void attachView(IBaseView<T> iBaseView);
    void detachView();
    void addListener(IBHomeData ibHomeData);
    void removeListener();
}
