package com.example.p2pfinancial.presenter;

import android.util.Log;

import com.example.base.BasePresenter;
import com.example.net.Constant;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class RegisterPresenter extends BasePresenter<String> {

    private HashMap<String, String> map = new HashMap<>();

    public RegisterPresenter() {
    }

    public RegisterPresenter(HashMap<String, String> map) {
        this.map = map;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public Type getType() {
        return new TypeToken<ResponseBody>() {
        }.getType();
    }

    @Override
    public HashMap<String, String> getQueryMap() {
        return map;
    }

    @Override
    public String getPath() {
        return "UserRegister";
    }
}
