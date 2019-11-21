package jni.example.p2pinvest.mvp.presenter;

import java.lang.reflect.Type;
import java.util.HashMap;

import jni.example.base.BasePresenter;
import jni.example.common.Constant;
import jni.example.p2pinvest.bean.UserRegisterBean;

public class RegisterPresenter extends BasePresenter<UserRegisterBean> {
    private HashMap<String,String> map;

    public RegisterPresenter(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public String getPath() {
        return Constant.USERREGISTER;
    }

    @Override
    public Type getBeanType() {
        return UserRegisterBean.class;
    }

    @Override
    public HashMap<String, String> getParams() {
        return map;
    }
}
