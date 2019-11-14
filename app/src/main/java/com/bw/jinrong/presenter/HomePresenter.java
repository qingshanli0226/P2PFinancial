package com.bw.jinrong.presenter;

import com.bw.base.BasePresenter;
import com.bw.common.AppNetConfig;
import com.bw.jinrong.bean.HomeBean;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<HomeBean> {
    @NotNull
    @Override
    public String getPath() {
        return new AppNetConfig().getINDEX();
    }

    @NotNull
    @Override
    public Type getBeanType() {
        return HomeBean.class;
    }

    @Override
    public boolean isList() {
        return false;
    }
}
