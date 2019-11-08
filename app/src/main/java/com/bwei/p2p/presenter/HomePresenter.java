package com.bwei.p2p.presenter;

import android.util.Log;

import com.bwei.base.BasePresenter;

import com.bwei.base.bean.Index;
import com.bwei.net.AppNetConfig;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;

public class HomePresenter extends BasePresenter<Index> {
//    HashMap<String,String> parmas;
//    public HomePresenter(HashMap<String,String> parmas) {
//    this.parmas=parmas;
//    }
//
//    @Override
//    public HashMap<String, String> getParmas() {
//        return parmas;
//    }

    @Override
    public Type getBeanType() {
        Log.i("ssss", "getBeanType");
        return  new TypeToken<Index>(){}.getType();
    }

    @Override
    public String getPath() {
        return AppNetConfig.INDEX;
    }

}
