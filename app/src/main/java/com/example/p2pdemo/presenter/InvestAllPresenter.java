package com.example.p2pdemo.presenter;

import com.example.modulebase.BasePresenter;
import com.example.p2pdemo.bean.ProductBean;

import java.lang.reflect.Type;

public class InvestAllPresenter extends BasePresenter {
    @Override
    protected Type getBeanType() {
        return ProductBean.class;
    }

    @Override
    protected String getPath() {
        return "product";
    }

    @Override
    public boolean isList() {
        return false;
    }
}
