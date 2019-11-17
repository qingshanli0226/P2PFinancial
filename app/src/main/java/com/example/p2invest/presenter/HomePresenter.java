package com.example.p2invest.presenter;

import com.example.base.BasePresenter;
import com.example.base.IBHomeData;
import com.example.net.AppNetConfig;
import com.example.net.BannerData;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<BannerData> {
    @Override
    public Type getBeanType() {
        return BannerData.class;
    }

    @Override
    public String getPath() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void removeListener() {

    }
}
