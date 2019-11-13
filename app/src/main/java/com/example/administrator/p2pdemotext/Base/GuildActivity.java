package com.example.administrator.p2pdemotext.Base;

import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Presenter.HomePresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;

import java.util.List;

public class GuildActivity extends BaseActivity<Bean> {

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
    public void onGetDataListSucess(List data) {

    }


    @Override
    public void onGetDataSucess(Bean data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
