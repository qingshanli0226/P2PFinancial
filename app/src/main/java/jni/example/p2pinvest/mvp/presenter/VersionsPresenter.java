package jni.example.p2pinvest.mvp.presenter;

import java.lang.reflect.Type;

import jni.example.base.BasePresenter;
import jni.example.common.Constant;
import jni.example.p2pinvest.bean.UpDate;

public class VersionsPresenter extends BasePresenter {

    @Override
    public String getPath() {
        return Constant.UPDATE;
    }

    @Override
    public Type getBeanType() {
        return UpDate.class;
    }
}
