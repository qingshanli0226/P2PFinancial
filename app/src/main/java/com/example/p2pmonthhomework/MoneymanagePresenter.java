package com.example.p2pmonthhomework;

import com.example.base.BasePresenter;
import com.example.p2pmonthhomework.bean.MoneymanageBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.common.Constant.PRODUCT;

public class MoneymanagePresenter extends BasePresenter<MoneymanageBean> {

    @Override
    public String getPath() {
        return PRODUCT;
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<MoneymanageBean>(){}.getType();
    }

    @Override
    public boolean isList() {
        return false;
    }
}
