package com.example.p2pfiancial.fragment.homefragment;

import com.example.base.presenter.BasePresenter;
import com.example.p2pfiancial.bean.HomeBannerBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter {
    @Override
    protected Type getBeanType() {
        return new TypeToken<HomeBannerBean>() {
        }.getType();
    }

    @Override
    protected String getPath() {
        return "index";
    }

}
