package com.example.p2pdemo.Presenter;

import com.example.base.BasePresenter;
import com.example.common.AppNetWork;
import com.example.net.HomeBaen;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<HomeBaen> {
    @Override
    public String getPath() {
        return AppNetWork.INDEX;
    }

    @Override
    public Type getBeanType() {
        return HomeBaen.class;
    }

    @Override
    public boolean isList() {
        return true;
    }

}
