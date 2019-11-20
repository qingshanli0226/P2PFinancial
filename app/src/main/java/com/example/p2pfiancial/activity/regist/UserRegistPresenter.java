package com.example.p2pfiancial.activity.regist;

import com.example.base.presenter.BasePresenter;
import com.example.p2pfiancial.bean.RegisterBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class UserRegistPresenter extends BasePresenter<RegisterBean> {
    private HashMap<String, String> map;

    public UserRegistPresenter(){

    }

    public UserRegistPresenter(HashMap<String, String> map){
        this.map = map;
    }

    @Override
    protected Type getBeanType() {
        return new TypeToken<RegisterBean>(){}.getType();
    }

    @Override
    protected String getPath() {
        return "UserRegister";
    }

    @Override
    protected HashMap<String, String> getParams() {
        return map;
    }
}
