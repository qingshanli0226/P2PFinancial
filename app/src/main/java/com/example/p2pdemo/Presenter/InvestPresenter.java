package com.example.p2pdemo.Presenter;

import com.example.base.BasePresenter;
import com.example.common.AppNetWork;
import com.example.p2pdemo.Bean.InvestBean;

import java.lang.reflect.Type;

public class InvestPresenter extends BasePresenter<InvestBean> {
    @Override
    public String getPath() {
        return AppNetWork.PRODUCT;
    }

    @Override
    public Type getBeanType() {
        return InvestBean.class;
    }

    @Override
    public boolean isList() {
        return false;
    }
}
