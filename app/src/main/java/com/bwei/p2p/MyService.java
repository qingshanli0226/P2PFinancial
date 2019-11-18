package com.bwei.p2p;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bwei.base.IBasePresenter;
import com.bwei.base.IbaseDataCache;
import com.bwei.p2p.presenter.HomePresenter;


public class MyService extends Service {
    private CacheManager cacheManager;
    private IBasePresenter presenter;
    public  final IBinder mBinder=new LocalBinder();
    public class LocalBinder extends Binder {
     // 在Binder中定义一个自定义的接口用于数据交互
          // 这里直接把当前的服务传回给宿主
               public MyService getService(){
                  return MyService.this;
                }
     }

    //写一个设置接口监听的方法
    public void registerListener(IbaseDataCache listener) {
        presenter.addListener(listener);

    }
    public void unRegisterListener() {
        presenter.unListener();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        presenter=new HomePresenter();
    }

    public void getData(){
        presenter.getDate();
    }
}
