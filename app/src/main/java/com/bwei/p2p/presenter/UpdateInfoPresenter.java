package com.bwei.p2p.presenter;
import com.bwei.base.BasePresenter;
import com.bwei.base.bean.UpdateInfo;
import com.bwei.net.AppNetConfig;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class UpdateInfoPresenter extends BasePresenter<UpdateInfo> {
    @Override
    public HashMap<String, String> getHearerParmas() {
        return new HashMap<>();
    }

    @Override
    public Type getBeanType() {
        return  new TypeToken<UpdateInfo>(){}.getType();
    }

    @Override
    public String getPath() {
        return AppNetConfig.UPDATE;
    }
}

