package com.bw.jinrong.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.bw.common.AppNetConfig;
import com.bw.jinrong.bean.HomeBean;
import com.bw.jinrong.bean.UpdateBean;
import com.bw.net.RetrofitCreator;
import com.google.gson.Gson;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.io.IOException;

public class CacheService extends Service {

    private IHomeDataListener iHomeDataListener;

    //谁维护接口，谁定义
    public interface IHomeDataListener{
        void onHomeDataReceived(HomeBean bean);
        void onUpdateApkBean(UpdateBean updateBean);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new CacheBinder();
    }

    public class CacheBinder extends Binder{
        public CacheService getCacheService(){
            return CacheService.this;
        }
    }

    public void registerListener(IHomeDataListener listener){
        this.iHomeDataListener = listener;
    }

    public void unRegisterListener(){
        this.iHomeDataListener = null;
    }

    //获取数据
    public void getHomeData(){
        new RetrofitCreator().getApiService().getMyDate(AppNetConfig.INSTANCE.getINDEX())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        HomeBean bean = null;
                        try {
                            bean = new Gson().fromJson(responseBody.string(), HomeBean.class);
                            iHomeDataListener.onHomeDataReceived(bean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUpdate(){
        new RetrofitCreator().getApiService().getMyDate(AppNetConfig.INSTANCE.getUPDATE())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            UpdateBean bean = new Gson().fromJson(responseBody.string(),UpdateBean.class);
                            iHomeDataListener.onUpdateApkBean(bean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
