package com.example.base;

import com.example.common.P2PError;

import java.util.List;

public interface IBaseView<T> {
    //请求json对象成功
    void onGetDataSucess(int requestCode, T data);

    //请求json列表成功
    void onGetDataListSucess(int requestCode, List<T> data);

    //请求数据失败
    void onGetDataFailed(int requestCode, P2PError error);

    //加载中页面
    void onLoading();

    //停止加载页面
    void onStopLoading();
}
