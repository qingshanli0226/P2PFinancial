package com.example.p2pdemo.Presenter;

import android.net.NetworkInfo;

import com.example.base.BasePresenter;
import com.example.base.IBasePresenter;
import com.example.common.AppNetWork;
import com.example.p2pdemo.Bean.RegisterBean;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RegisterPersenter extends BasePresenter<RegisterBean> {

    HashMap<String,String> maps;
    @Override
    public String getPath() {
        return AppNetWork.FEEDBACK;
    }

    @Override
    public Type getBeanType() {
        return RegisterBean.class;
    }


    public void getMap(HashMap<String,String> map){
        maps=map;
    }

    @Override
    public HashMap<String, String> getParams() {
        return maps ;
    }
}
