package com.example.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static IRetrofit interRetrofit;
    public static IRetrofit getIRetrofit(){
        if (interRetrofit==null){
            getInstance();
        }
        return interRetrofit;
    }
    private static void getInstance(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(NetWorkStringUtil.BASE_URL)
                .build();
        interRetrofit = retrofit.create(IRetrofit.class);
    }
}
