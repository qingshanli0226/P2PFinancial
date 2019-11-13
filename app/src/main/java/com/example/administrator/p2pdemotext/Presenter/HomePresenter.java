package com.example.administrator.p2pdemotext.Presenter;

import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.base.BasePresenter;
import com.example.base.IBasePresenter;
import com.example.net.ResEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class HomePresenter extends BasePresenter<Bean> {
    @Override
    public String getpath() {
        return "index";
    };


    @Override
    public Type getBeanType() {
        return new TypeToken<Bean>(){}.getType();
    }

    @Override
    public void attachView(IBasePresenter ibaseView) {

    }
    @Override
    public boolean isList(){
        return false;
    }
}
