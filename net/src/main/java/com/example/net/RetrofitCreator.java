package com.example.net;


import com.example.common.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    public static IRetrofit iRetrofit;

    public static IRetrofit getApiService() {
        if (iRetrofit == null) {
            createApiService();
        }

        return iRetrofit;
    }

    private static void createApiService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        if (Constant.PRINT_LOG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //确保service方法，返回值是Observable.
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient) //okhttpclient
                .baseUrl(Constant.BASE_URL) //base url
                .build();

        iRetrofit = retrofit.create(IRetrofit.class);
    }


}
