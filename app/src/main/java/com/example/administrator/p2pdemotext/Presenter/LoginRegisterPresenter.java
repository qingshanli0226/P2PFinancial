package com.example.administrator.p2pdemotext.Presenter;

import com.example.administrator.p2pdemotext.Base.LoginRegisterBean;
import com.example.base.BasePresenter;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LoginRegisterPresenter extends BasePresenter<LoginRegisterBean> {
    HashMap<String,String> hashMap;

    public LoginRegisterPresenter(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String getpath() {
        return "login";
    }

    @Override
    public Type getBeanType() {
        return LoginRegisterBean.class;
    }

    @Override
    public HashMap<String, String> getparmas() {
        return hashMap;
    }
}
