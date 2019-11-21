package com.example.administrator.p2pdemotext.Presenter;

import com.example.administrator.p2pdemotext.Base.AllBean;
import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.base.BasePresenter;
import com.example.base.IBasePresenter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class InvestAllPresenter extends BasePresenter<AllBean> {
    @Override
    public String getpath() {
        return "product";
    };


    @Override
    public Type getBeanType() {
        return new TypeToken<AllBean>(){}.getType();
    }


    @Override
    public boolean isList(){
        return false;
    }
}
