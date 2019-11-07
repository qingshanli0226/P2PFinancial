package com.example.p2pdemo.presenter;

import com.example.modulebase.BasePresenter;
import com.example.p2pdemo.bean.HomeBean;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<HomeBean> {


    @Override
    protected Type getBeanType() {
        return HomeBean.class;
    }

    @Override
    protected String getPath() {
        return "index";
    }

    @Override
    public boolean isList() {
        return false;
    }
}
