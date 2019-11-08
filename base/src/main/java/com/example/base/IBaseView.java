package com.example.base;

import java.util.List;

public interface IBaseView<T> {

    void onGetDataSucess(int requestCode, T data);

    void onGetDataListSucess(int requestCode, List<T> data);

    void onGetDataFailed(int requestCode, String message);

    void onLoading();

    void onStopLoading();
}
