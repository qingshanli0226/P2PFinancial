package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {
    public static NetApiService netApiService;
    public static NetApiService getNetApiService(){
        if (netApiService==null){
            createApiService();
        }
        return netApiService;
    }

    private static void createApiService() {
        //拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constant.BASE_URL)
                .build();

        netApiService=retrofit.create(NetApiService.class);

    }
}
