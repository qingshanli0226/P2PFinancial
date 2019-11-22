package com.example.p2pdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.common.AppNetWork;
import com.example.net.RetrofitCreator;
import com.example.p2pdemo.Bean.HomeBaen;
import com.example.p2pdemo.Bean.UpdateBean;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class CacheService extends Service {


    private IHomeDataListener iHomeDataListener;
    public interface IHomeDataListener{
        void onHomeDataReceived(HomeBaen homeBaen);
        void onUpdateApkBean(UpdateBean updateBean);
    }
    public class MyBinder extends Binder{
        public CacheService getCacheService(){
            return CacheService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void registerListener(IHomeDataListener dataListener){
        this.iHomeDataListener=dataListener;
    }
    public void unregisterListener(){
        this.iHomeDataListener=null;
    }

    public void getData(){
        RetrofitCreator.getNetApiService().getMyData(AppNetWork.INDEX)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            HomeBaen bean = new Gson().fromJson(responseBody.string(), HomeBaen.class);
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

    public void getUpdateBean(){
        RetrofitCreator.getNetApiService().getMyData(AppNetWork.UPDATE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            UpdateBean toJson = (UpdateBean) new Gson().fromJson(responseBody.string(), UpdateBean.class);
                            iHomeDataListener.onUpdateApkBean(toJson);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("##Up",""+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




}
