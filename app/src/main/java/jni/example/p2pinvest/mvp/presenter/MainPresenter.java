package jni.example.p2pinvest.mvp.presenter;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import jni.example.base.BasePresenter;
import jni.example.common.Constant;
import jni.example.p2pinvest.bean.Index;

public class MainPresenter extends BasePresenter<Index> {

    @Override
    public String getPath() {
        return Constant.INDEX;
    }

    @Override
    public Type getBeanType() {
        return Index.class;
    }
}
