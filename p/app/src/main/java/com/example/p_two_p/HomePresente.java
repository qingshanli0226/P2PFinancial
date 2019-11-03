package com.example.p_two_p;

import com.example.base.BasePresenter;

import java.lang.reflect.Type;
import java.util.HashMap;

public class HomePresente extends BasePresenter<TestBean> {
    @Override
    public String getPath() {
        return null;
    }

    @Override
    public Type getBeanType() {
        return null;
    }

    @Override
    public HashMap<String, String> getHeaderParms() {
        return super.getHeaderParms();
    }

    @Override
    public HashMap<String, String> getParams() {
        return super.getParams();
    }
}
