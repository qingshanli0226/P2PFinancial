package com.example.month6.presenter;

import com.example.common.diyviews.presenter.BasePresenter;

import java.lang.reflect.Type;

public class ServicePresenter extends BasePresenter<Object> {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    protected Type getDataClass() {
        return type;
    }

    @Override
    protected String setUrlPath() {
        return null;
    }
}
