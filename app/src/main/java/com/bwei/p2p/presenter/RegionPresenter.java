package com.bwei.p2p.presenter;

import com.bwei.base.BasePresenter;
import com.bwei.base.bean.Products;
import com.bwei.net.AppNetConfig;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RegionPresenter extends BasePresenter<Products> {
    HashMap<String,String> parmas;
    public RegionPresenter(HashMap<String,String> parmas) {
    this.parmas=parmas;
    }

    @Override
    public Type getBeanType() {
        return null;
    }

    @Override
    public HashMap<String, String> getParmas() {
        if (parmas==null){
            parmas=new HashMap<>();
        }
        return parmas;
    }
    public RegionPresenter() {
    }

    @Override
    public HashMap<String, String> getHearerParmas() {
        return new HashMap<>();
    }


    @Override
    public String getPath() {
        return AppNetConfig.USERREGISTER;
    }

}
