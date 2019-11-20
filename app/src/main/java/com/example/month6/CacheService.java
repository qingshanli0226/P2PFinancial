package com.example.month6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.common.diyviews.presenter.BasePresenter;
import com.example.month6.presenter.ServicePresenter;

import java.lang.reflect.Type;

public class CacheService extends Service {

    //用于manager和service的通信
    CacheServiewlistener cacheServiewlistener;
    //网络和service的传输
    ServicePresenter presenter=new ServicePresenter();

    public void setCacheServiewlistener(CacheServiewlistener cacheServiewlistener) {
        this.cacheServiewlistener = cacheServiewlistener;
    }

    public void getData(Type type){
        //
        presenter.setType(type);
        presenter.sendPostRequest();
        cacheServiewlistener.onSuccess(null);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("xxx","调用onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("xxx","创建了服务");
    }

    public class MyBinder extends Binder{
        public CacheService getSetvice(){
            return CacheService.this;
        }
    }
}
