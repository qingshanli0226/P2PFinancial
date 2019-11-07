package com.example.net;

import com.example.common.AppNetWork;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {


    public static NetApiService netApiService;

    public static NetApiService getNetApiService(){
        if(netApiService==null){
            createApi();
        }
        return netApiService;
    }

    private static void createApi() {

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(50,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit.Builder builder1 = new Retrofit.Builder();
        Retrofit retrofitBuild = builder1
                .baseUrl(AppNetWork.BASE_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        netApiService=retrofitBuild.create(NetApiService.class);
    }

}
