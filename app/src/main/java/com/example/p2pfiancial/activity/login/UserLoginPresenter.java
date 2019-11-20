package com.example.p2pfiancial.activity.login;

import com.example.base.presenter.BasePresenter;
import com.example.p2pfiancial.bean.LoginBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class UserLoginPresenter extends BasePresenter<LoginBean> {
    private HashMap<String, String> map;

    public UserLoginPresenter(){

    }

    public UserLoginPresenter(HashMap<String, String> map){
        this.map = map;
    }

    @Override
    protected Type getBeanType() {
        return new TypeToken<LoginBean>(){}.getType();
    }

    @Override
    protected String getPath() {
        return "login";
    }

    @Override
    protected HashMap<String, String> getParams() {
        return map;
    }
}
