package com.bwei.base;

import java.util.HashMap;

public interface IBasePresenter<T> {
    void getDate(String cat, HashMap<String,String> params);
    void attachView(IbaseView<T> ibaseView);
    void datachView();
}
