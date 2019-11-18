package jni.example.base;

import java.util.List;

public interface IGetDateListener<T> {
    void onGetDataFailed(String msg);
    void onGetDataSuccess(int requestCode,T data);
    void onGetDataListSuccess(List<T> data);
}
