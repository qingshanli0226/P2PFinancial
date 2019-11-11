package com.example.p2pfiancial.fragment.investfragment;

import com.example.base.presenter.BasePresenter;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class InvestPresenter extends BasePresenter<InvestProductBean> {
    @Override
    protected Type getBeanType() {
        return new TypeToken<InvestProductBean>() {
        }.getType();
    }

    @Override
    protected String getPath() {
        return "product";
    }
}
