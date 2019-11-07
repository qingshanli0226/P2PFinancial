package com.example.administrator.p2pdemotext.Base;

import com.example.administrator.p2pdemotext.Base.Bean;
import com.example.base.BasePresenter;
import com.example.base.IBasePresenter;
import com.example.net.ResEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class HomePresenter extends BasePresenter<Bean> {
    @Override
    public String getpath() {
        return "dish_list.php";
    };

    @Override
    public HashMap<String,String> getParmas(){
        HashMap<String,String> paramMap=new HashMap<>();
        paramMap.put("stage_id", "1");
        paramMap.put("limit", "20");
        paramMap.put("page", "1");
        return paramMap;
    }
    @Override
    public Type getBeanType() {
        return new TypeToken<ResEntity<List<Bean>>>(){}.getType();
    }

    @Override
    public void attachView(IBasePresenter ibaseView) {

    }
    @Override
    public boolean isList(){
        return false;
    }
}
