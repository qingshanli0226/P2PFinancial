package com.example.p2pdemo.presenter;

import com.example.modulebase.BasePresenter;
import com.example.modulebase.User;
import com.example.p2pdemo.bean.UserBean;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LoginPresenter extends BasePresenter {
    private String phone;
    private String pwd;

    public LoginPresenter(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    @Override
    protected Type getBeanType() {
        return UserBean.class;
    }

    @Override
    protected String getPath() {
        return "login";
    }

    @Override
    public HashMap<String, String> getParmas() {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",this.phone);
        map.put("password",this.pwd);
        return map;
    }


}
