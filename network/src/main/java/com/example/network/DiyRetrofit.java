package com.example.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiyRetrofit {
    private static IRetrofit interRetrofit;
    public static IRetrofit getInterRetrofit(){
        if (interRetrofit==null){
            createInter();
        }
        return interRetrofit;
    }
    private static void createInter(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(NetStringUtils.BASE_URL)
                .build();
        interRetrofit = retrofit.create(IRetrofit.class);
    }
}
