package com.example.p2pdemo.presenter;

import com.example.modulebase.BasePresenter;
import com.example.p2pdemo.bean.RegisterBean;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RegisterPresenter extends BasePresenter {
    private String name;
    private String password;
    private String phone;

    public RegisterPresenter(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    @Override
    protected Type getBeanType() {
        return RegisterBean.class;
    }

    @Override
    protected String getPath() {
        return "UserRegister";
    }

    @Override
    public HashMap<String, String> getParmas() {
       HashMap map =  new HashMap<String,String>();
       map.put("name",this.name);
       map.put("password",this.password);
       map.put("phone",this.phone);
        return map;
    }
}
