package jni.example.p2pinvest.mvp.model;

import jni.example.lib_core.mvp.model.BaseModel;
import jni.example.p2pinvest.mvp.contract.MainContract;

public class MainModel extends BaseModel implements MainContract.MainIModel {
    @Override
    public void destroy() {

    }

    @Override
    public String requestMain(String str) {
        return "requestMain请求响应";
    }
}
