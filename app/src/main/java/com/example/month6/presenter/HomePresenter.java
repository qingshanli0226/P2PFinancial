package com.example.month6.presenter;

import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.month6.databean.HomeData;
import com.example.network.NetStringUtils;

import java.lang.reflect.Type;

public class HomePresenter extends DiyPresenter<HomeData> {
    @Override
    protected Type getDataClass() {
        return HomeData.class;
    }

    @Override
    protected String setUrlPath() {
        return NetStringUtils.INDEX;
    }
}
