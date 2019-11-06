package com.bwei.p2p.present;

import android.util.Log;

import com.bwei.base.BasePresenter;

import com.bwei.p2p.Index;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<Index> {
    @Override
    public Type getBeanType() {
        Log.i("ssss", "getBeanType");
        return  new TypeToken<Index>(){}.getType();
    }

    @Override
    public boolean isList() {
        return false;
    }
}
