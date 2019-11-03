package com.example.base;

import java.util.List;

public interface IBaseView<T> {

    void onGetDataSucess(T data);

    void onGetDataListSucess(List<T> data);

    void onGetDataFailed(String message);
}
