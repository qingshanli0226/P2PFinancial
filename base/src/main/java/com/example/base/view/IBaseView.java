package com.example.base.view;

import com.example.commen.P2PError;

import java.util.List;

/**
 * 它是被presenter回调的
 * presenter拿到数据后 通过view回调方法
 * 这个接口就是view的通用回调接口
 *
 * 因为要支持不同的Activity Fragment, 所以使用泛型
 */
public interface IBaseView<T> {
    //通过requestCode 进行多次访问
    void onHttpRequestDataSuccess(int requestCode, T data);
    void onHttpRequestDataListSuccess(int requestCode, List<T> data);
    void onHttpRequestDataFailed(int requestCode, P2PError error);

    //单次访问网络
//    void onGetDataSuccess(T data); //数据
//    void onGetDataListSuccess(List<T> data); //List类型数据
//    void onGetDataFailed(String message); //数据请求失败

    void showLoading(int showCode); //开始请求数据时, 后显示加载页面
    void hideLoading(int showCode); //请求数据结束时, 关闭显示加载页面
}
