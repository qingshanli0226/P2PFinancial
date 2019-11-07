package com.example.p2invest.presenter;

import com.example.base.BasePresenter;
import com.example.net.BannerData;

import java.lang.reflect.Type;

public class Home_Presenter extends BasePresenter<BannerData> {
    @Override
    public Type getBeanType() {
        return BannerData.class;
    }
}
