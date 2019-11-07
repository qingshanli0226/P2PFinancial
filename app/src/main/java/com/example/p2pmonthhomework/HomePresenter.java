package com.example.p2pmonthhomework;

import com.example.base.BasePresenter;
import com.example.net.ResEntity;
import com.example.p2pmonthhomework.bean.HomeBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.example.common.Constant.INDEX;

public class HomePresenter extends BasePresenter<HomeBean> {

    @Override
    public String getPath() {
        return INDEX;
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<HomeBean>(){}.getType();
    }

    @Override
    public boolean isList() {
        return false;
    }
}
