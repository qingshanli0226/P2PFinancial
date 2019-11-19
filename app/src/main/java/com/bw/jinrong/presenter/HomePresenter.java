package com.bw.jinrong.presenter;

import com.bw.base.BasePresenter;
import com.bw.common.AppNetConfig;
import com.bw.jinrong.bean.HomeBean;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<HomeBean> {

    private String Config;
    private Type type;

    public HomePresenter() {
    }

    public HomePresenter(String config, Type type) {
        Config = config;
        this.type = type;
    }

    @NotNull
    @Override
    public String getPath() {
        return Config;
    }

    @NotNull
    @Override
    public Type getBeanType() {
        return type;
    }

    @Override
    public boolean isList() {
        return false;
    }
}
