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



}
