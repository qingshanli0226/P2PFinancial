package com.example.modulebase;

import java.util.List;

public interface IBaseView<T> {
    void onLoadDataSuccess(int requestCode,T data);
    void onLoadDataListSuccess(int requestCode, List<T> data);
    void onLoadDataPostSuccess(int requestCode,T data);
//    void onLoadDataFailed(int requestCode,)
    void showLoading(int requestCode);//开始请求数据时，后显示加载页面
    void hideLoading(int requestCode);//请求数据结束时，关闭显示加载页面.
}
