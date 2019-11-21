package jni.example.p2pinvest.mvp.presenter;

import java.lang.reflect.Type;
import java.util.HashMap;

import jni.example.base.BasePresenter;
import jni.example.common.Constant;
import jni.example.p2pinvest.bean.UserBean;

public class LoginPresenter extends BasePresenter<UserBean> {

    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPath() {
        return Constant.LOGIN;
    }

    @Override
    public Type getBeanType() {
        return UserBean.class;
    }

    @Override
    public HashMap<String, String> getParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("password",password);
        return hashMap;
    }
}
