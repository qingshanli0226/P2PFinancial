package com.example.p2invest.presenter;

import com.example.base.BasePresenter;
import com.example.base.IBHomeData;
import com.example.net.AppNetConfig;
import com.example.net.ProductData;

import java.lang.reflect.Type;

public class ZiChanPresenter extends BasePresenter<ProductData> {
    @Override
    public Type getBeanType() {
        return ProductData.class;
    }

    @Override
    public String getPath() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    public void addListener(IBHomeData ibHomeData) {

    }

    @Override
    public void removeListener() {

    }
}
