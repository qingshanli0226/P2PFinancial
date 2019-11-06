package com.example.p2pfinancial.presenter;

import com.example.base.BasePresenter;
import com.example.p2pfinancial.bean.MainBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainPresenter extends BasePresenter<MainBean> {


    @Override
    public String getPath() {
        return "index";
    }

    @Override
    public Type getType() {
        return new TypeToken<MainBean>() {
        }.getType();
    }
}
