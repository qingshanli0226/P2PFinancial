package com.example.administrator.p2pdemotext.Presenter;

import com.example.administrator.p2pdemotext.Base.UserRegisterBean;
import com.example.base.BasePresenter;
import com.example.base.IBasePresenter;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RegisterPresenter extends BasePresenter<UserRegisterBean> {
    HashMap<String,String> hashMap;

    public RegisterPresenter(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }


    @Override
    public String getpath() {
        return "UserRegister";
    }

    @Override
    public Type getBeanType() {
        return UserRegisterBean.class;
    }

    @Override
    public HashMap<String, String> getparmas() {

        return hashMap;

    }
}
