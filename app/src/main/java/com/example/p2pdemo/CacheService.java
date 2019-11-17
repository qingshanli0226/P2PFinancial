package com.example.p2pdemo;

import android.app.Service;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.base.utils.ErroUtils;
import com.example.common.AppNetWork;
import com.example.net.RetrofitCreator;
import com.example.p2pdemo.Bean.HomeBaen;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CacheService extends Service {


    private IHomeDataListener iHomeDataListener;
    public interface IHomeDataListener{


        void HomeDataReceived(HomeBaen homeBaen);
    }
    class MyBinder extends Binder{
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
                            iHomeDataListener.HomeDataReceived(bean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("##","4");

                    }
                });



    }




}
