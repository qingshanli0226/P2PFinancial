package com.example.administrator.p2pdemotext.Base;

import android.os.Bundle;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.Base.HomePresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.base.IBasePresenter;

public class GuildActivity extends BaseActivity  {

    //申明接口
    private IBasePresenter iBasePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild);
        iBasePresenter=new HomePresenter();
        iBasePresenter.attachView(this);


    }



}
