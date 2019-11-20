package com.example.common.diyviews.presenter;

import java.io.IOException;

public interface IPresenter<T> {
    void sendGetRequest();
    void sendPostRequest();
    void setIBaseView(IBaseView<T> presenterBaseView);
    void destoryIBaseView();
}
