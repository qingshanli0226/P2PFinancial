package com.bwei.base;

import java.util.HashMap;

public interface IBasePresenter<T> {
    void getDate();
    void attachView(IbaseView<T> ibaseView);
    void datachView();
}
