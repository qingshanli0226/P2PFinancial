package com.example.base;

import java.util.List;

public interface IBaseView<T> {
    void onGetDataSucces(T data);
    void onGetDataListSuccess(List<T> data);
    void onGetDataFailed(String message);
    void showLoading();
    void hideLoading();
}
