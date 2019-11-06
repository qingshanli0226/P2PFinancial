package jni.example.base;

import java.util.List;

public interface IView<T> {
    void showLoading();
    void hideLoading();
    void onGetDataFailed(String msg);//请求数据失败
    void onGetDataSuccess(T data);
    void onGetDataListSuccess(List<T> data);//返回列表的回调
}
