package com.example.base.presenter;

import com.example.base.view.IBaseView;

/**
 * 获取数据, 各个页面都可以调用该接口获取数据
 */
public interface IBasePresenter<T> {
    void getData(); //从net包中拿数据
    void attachView(IBaseView<T> iBaseView); //关联IBaseView
    void detachView(); //解除关联
}
