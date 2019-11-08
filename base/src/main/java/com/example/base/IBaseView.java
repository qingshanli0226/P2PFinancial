package com.example.base;

import com.example.common.ErrorCodes;

import java.util.List;

public interface IBaseView<T> {
    void onGetDataSuccess(T data);
    void onGetDataListSuccess(List<T> data);
    void onGetDataFailed(String message, ErrorCodes codes);
    void showLoading();
    void hideLoading();
}
