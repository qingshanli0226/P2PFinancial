package jni.example.base;

import android.view.View;

import java.util.List;

public interface IView<T> {
    //TODO 显示加载页面
    void showLoading();
    //TODO 隐藏加载页面
    void hideLoading();
    //TODO 显示错误页面
    void showErrorPage();
    //TODO 隐藏错误页面
    void hideErrorPage();
    //TODO 请求数据失败
    void onGetDataFailed(String msg);
    //TODO get请求对象数据
    void onGetDataSuccess(T data);
    //TODO get请求列表数据
    void onGetDataListSuccess(List<T> data);
}
