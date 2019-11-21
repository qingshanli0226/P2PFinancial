package com.example.net;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    private static NetApiService netApiService;
    private static NetPostApiService netPostApiService;

    //饿汉单例
    public static NetApiService getNetApiService(String baseUrl) {
        if (netApiService == null) {
            createNetApiService(baseUrl);
        }
        return netApiService;//返回单例对象
    }

    public static NetPostApiService getNetPostApiService(String baseUrl) {
        if (netPostApiService == null) {
            createNewPostApiService(baseUrl);
        }
        return netPostApiService;
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

    private static void createNewPostApiService(String path) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(build)
                .build();

        netPostApiService = retrofit.create(NetPostApiService.class);
    }
}
