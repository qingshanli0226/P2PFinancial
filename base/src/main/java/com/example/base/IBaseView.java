package com.example.base;

import com.example.common.ErrorCodes;

import java.util.List;

public interface IBaseView<T> {
    void onGetDataSuccess(int requestCode,T data);
    void onGetDataListSuccess(int requestCode,List<T> data);
    void onGetDataFailed(int requestCode, ErrorCodes codes);
}
