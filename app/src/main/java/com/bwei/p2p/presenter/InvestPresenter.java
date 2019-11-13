package com.bwei.p2p.presenter;

import android.util.Log;

import com.bwei.base.BasePresenter;
import com.bwei.base.bean.Index;
import com.bwei.base.bean.Products;
import com.bwei.net.AppNetConfig;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class InvestPresenter extends BasePresenter<Products> {
    HashMap<String,String> parmas;
    public InvestPresenter(HashMap<String,String> parmas) {
    this.parmas=parmas;
    }
    @Override
    public HashMap<String, String> getParmas() {
        if (parmas==null){
            parmas=new HashMap<>();
        }
        return parmas;
    }
    public InvestPresenter() {
    }

    @Override
    public HashMap<String, String> getHearerParmas() {
        return new HashMap<>();
    }

    @Override
    public Type getBeanType() {
        Log.i("ssss", "getBeanType");
        return  new TypeToken<Products>(){}.getType();
    }

    @Override
    public String getPath() {
        return AppNetConfig.PRODUCT;
    }

}
