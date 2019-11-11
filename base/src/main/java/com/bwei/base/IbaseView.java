package com.bwei.base;

import com.bwei.common.P2PError;

import java.util.List;

//被presenter回调的P层拿到数据后
// view的通用回调接口
public interface IbaseView<T> {
    void onGetDataSucess(T data);
    void onGetDataListSucess(List<T> data);
    void onGetDataFailed(String message);
    void showLoading();//开始请求数据时，后显示加载页面
    void hideLoading(int i);//请求数据结束时，关闭显示加载页面.
    void onHttpRequestDataFailed(int requestCode, P2PError error);

}
