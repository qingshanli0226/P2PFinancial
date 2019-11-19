package com.bwei.p2p.presenter;

import com.bwei.base.BasePresenter;
import com.bwei.base.bean.Products;
import com.bwei.net.AppNetConfig;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LoginPresenter extends BasePresenter<Products> {
    HashMap<String,String> parmas;
    public LoginPresenter(HashMap<String,String> parmas) {
    this.parmas=parmas;
    }
    @Override
    public HashMap<String, String> getParmas() {
        if (parmas==null){
            parmas=new HashMap<>();
        }
        return parmas;
    }
    public LoginPresenter() {
    }

    @Override
    public HashMap<String, String> getHearerParmas() {
        return new HashMap<>();
    }

    @Override
    public Type getBeanType() {
        return  null;
    }

    @Override
    public String getPath() {
        return AppNetConfig.LOGIN;
    }

}
