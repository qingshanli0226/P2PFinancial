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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    protected Type getBeanType() {
        return UserBean.class;
    }

    @Override
    protected String getPath() {
        return "Login";
    }

    @Override
    public HashMap<String, String> getParmas() {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",this.getPhone());
        map.put("password",this.getPwd());
        return map;
    }


}
