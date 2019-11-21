package com.example.base;

import java.util.List;

public interface IBaseView<T> {
    //成功的
    void onGetDataSucess(T data);
    //返回列表的回调
    void onGetDataListSucess(List<T> data);
   //失败的
    void onGetDataFailed(String message);

    //加载
    void onShow(int code);

    //Post请求成功的
    void onHttpRequestDataSuccess(int requestCode,T data);

    //post请求列表的回调
    void onHttpRequestDataListSuccess(int requestCode, List<T> data);

    //Post请求失败的
    void onHttpRequestDataFailed(int requestCode);


}
