package com.example.base;

import com.example.common.P2PError;

import java.util.List;

public interface IBaseView<T> {

    void onGetDataSucess(int requestCode, T data);

    void onGetDataListSucess(int requestCode, List<T> data);

    void onGetDataFailed(int requestCode, P2PError error);

    void onLoading();

    void onStopLoading();
}
