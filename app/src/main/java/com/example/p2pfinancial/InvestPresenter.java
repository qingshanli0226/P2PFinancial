package com.example.p2pfinancial;

import com.example.base.BasePresenter;
import com.example.net.MainEntity;
import com.example.p2pfinancial.bean.InvestBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class InvestPresenter extends BasePresenter<InvestBean> {


    @Override
    public String getPath() {
        return "index";
    }

    @Override
    public Type getType() {
        return new TypeToken<MainEntity<InvestBean>>() {
        }.getType();
    }

    @Override
    public boolean isList() {
        return false;
    }
}
