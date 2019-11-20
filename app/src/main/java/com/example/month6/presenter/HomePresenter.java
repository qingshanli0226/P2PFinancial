package com.example.month6.presenter;

import com.example.common.diyviews.presenter.BasePresenter;
import com.example.month6.databean.HomeData;
import com.example.network.NetWorkStringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<HomeData> {
    @Override
    protected Type getDataClass() {
        return HomeData.class;
    }

    @Override
    protected String setUrlPath() {
        return NetWorkStringUtil.INDEX;
    }
}
