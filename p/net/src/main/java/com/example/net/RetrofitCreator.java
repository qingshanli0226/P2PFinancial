package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitCreator {


    public static NetApiService netApiService;

    public static NetApiService getNetApiService(){
        if(netApiService==null){
            createApi();
        }
        return netApiService;
    }

    private static void createApi() {

        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(50,TimeUnit.SECONDS)
                .build();

        Retrofit.Builder builder1 = new Retrofit.Builder();
        Retrofit retrofitBuild = builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder)
                .baseUrl(Contant.BASE_URL)
                .build();

        netApiService=retrofitBuild.create(NetApiService.class);
    }

}
