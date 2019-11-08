package com.example.administrator.p2pdemotext.Base;

import android.os.Bundle;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.Base.HomePresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;

import java.util.List;

public class GuildActivity extends BaseActivity implements IBaseView<Object> {

    //申明接口
    private IBasePresenter iBasePresenter;


    @Override
    protected void initData() {
        iBasePresenter=new HomePresenter();
        iBasePresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guild;
    }


    @Override
    public void onGetDataSucess(Object data) {

    }

    @Override
    public void onGetDataListSucess(List<Object> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
