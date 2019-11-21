package com.example.p2pfinancial.presenter;

import com.example.base.BasePresenter;
import com.example.net.ResEntity;
import com.example.p2pfinancial.bean.AllInvestBean2;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AllInvestPresenter extends BasePresenter<AllInvestBean2> {


    @Override
    public String getPath() {
        return "product";
    }

    @Override
    public Type getType() {
        return new TypeToken<AllInvestBean2>() {
        }.getType();
    }
}
