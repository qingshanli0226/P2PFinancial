package com.example.base;

import java.util.List;

public interface IBaseView<T> {
    void onGetDataSuccess(T data);
    void onGetDataListSuccess(List<T> data);
    void onGetDataFailed(String message);
    void showLoading();
    void hideLoading();
}
