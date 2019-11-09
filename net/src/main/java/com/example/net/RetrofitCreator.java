package com.example.net;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    public static NetApiService netApiService;

    //饿汉单例
    public static NetApiService getNetApiService(String baseUrl) {
        if (netApiService == null) {
            createNetApiService(baseUrl);
        }
        return netApiService;//返回单例对象
    }

    private static void createNetApiService(String baseUrl) {
        //log打印网络请求过程
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)//超时时间
                .addInterceptor(loggingInterceptor)//添加日志dayin
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        netApiService = retrofit.create(NetApiService.class);
    }
}
