package com.example.base;

import java.util.HashMap;

public interface IBsePresenter<T> {
    void getData(String path);
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
