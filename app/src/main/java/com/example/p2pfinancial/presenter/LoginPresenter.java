package com.example.p2pfinancial.presenter;

import com.example.base.BasePresenter;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LoginPresenter extends BasePresenter<String> {

    private HashMap<String, String> hashMap = new HashMap<>();

    public LoginPresenter(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String getPath() {
        return "login";
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public HashMap<String, String> getQueryMap() {
        return hashMap;
    }
}
