package com.example.common.diyviews.presenter;

public interface PresenterBaseView<T> {
    void setDataError(String str);
    void setDataSuccess(T object);
    void showLoadView();
    void hindLoadView();
    void findError();
}
