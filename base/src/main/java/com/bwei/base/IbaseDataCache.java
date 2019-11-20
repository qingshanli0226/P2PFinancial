package com.bwei.base;

import com.bwei.base.bean.Index;
import com.bwei.base.bean.UpdateInfo;

//被presenter回调的P层拿到数据后
// view的通用回调接口
public interface IbaseDataCache {
    void onGetDataSucess(Index data);
    void onGetVersion(UpdateInfo update);
}
