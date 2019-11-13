package com.example.month6.presenter;

import android.util.Log;

import com.example.common.diyviews.presenter.BasePresenter;
import com.example.month6.databean.AllShowData;
import com.example.network.NetWorkStringUtil;

import java.lang.reflect.Type;

public class ShowAllPresenter extends BasePresenter<AllShowData> {
    @Override
    protected Type getDataClass() {
        Log.e("xxxx","创建ShowAllPresenter");
        return AllShowData.class;
    }

    @Override
    protected String setUrlPath() {
        return NetWorkStringUtil.PRODUCT;
    }
}
