package com.bwei.p2p.present;

import com.bwei.base.BasePresenter;

import com.bwei.net.AppNetConfig;
import com.bwei.net.ResEntity;
import com.bwei.p2p.bean.Image;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class HomePresenter extends BasePresenter<Image> {
    @Override
    public Type getBeanType() {
        return new TypeToken<ResEntity<Image>>(){}.getType();
    }

    @Override
    public boolean isList() {
        return false;
    }
}
