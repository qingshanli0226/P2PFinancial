package com.bwei.base;

import com.bwei.base.bean.Index;

//被presenter回调的P层拿到数据后
// view的通用回调接口
public interface IbaseDataCache {
    void onGetDataSucess(Index data);
}
