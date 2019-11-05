package com.example.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiyRetrofit {
    private static InterRetrofit interRetrofit;
    public static InterRetrofit getInterRetrofit(){
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
                .baseUrl("")
                .build();
        interRetrofit = retrofit.create(InterRetrofit.class);
    }
}
