package com.bwei.p2p;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.bwei.base.IbaseDataCache;
import com.bwei.base.bean.Index;
import com.bwei.base.bean.UpdateInfo;
import com.bwei.net.AppNetConfig;
import com.bwei.net.RetrofitCreate;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MyService extends Service {
    private CacheManager cacheManager;
//    private IBasePresenter presenter;
    private IbaseDataCache ibaseDataCache;
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
        ibaseDataCache=listener;

    }
    public void unRegisterListener() {
        if (ibaseDataCache!=null){
        ibaseDataCache=null;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void getData(){
        RetrofitCreate.getNetApiService().getData(null, AppNetConfig.INDEX,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("SSSSSS", "onSubscribe: 服务开始获取数据");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Index index = JSONObject.parseObject(json, Index.class);
                            ibaseDataCache.onGetDataSucess(index);
                        } catch (IOException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("SSSSSS", "onError: 服务获取数据出错:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        RetrofitCreate.getNetApiService().getData(null, AppNetConfig.UPDATE,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("SSSSSS", "onSubscribe2: 服务开始获取数据");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            UpdateInfo update = JSONObject.parseObject(json, UpdateInfo.class);
                            ibaseDataCache.onGetVersion(update);
                        } catch (IOException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("SSSSSS", "onError: 服务获取数据出错:"+e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });

    }
}
