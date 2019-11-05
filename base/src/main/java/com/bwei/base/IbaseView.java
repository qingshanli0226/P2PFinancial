package com.bwei.base;

import java.util.List;

//被presenter回调的P层拿到数据后
// view的通用回调接口
public interface IbaseView<T> {
    void onGetDataSucess(T data);
    void onGetDataListSucess(List<T> data);
    void onGetDataFailed(String message);

}
