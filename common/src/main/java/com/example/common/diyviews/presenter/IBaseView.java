package com.example.common.diyviews.presenter;

public interface IBaseView<T> {
    //获取数据成功  获取数据失败  展示等待 隐藏等待 (等待与错误为view不同的同一对象)
    void getDataSuccess(T object);
    void getDataError();
    void showLoadView();
    void hideLoadView();
}
