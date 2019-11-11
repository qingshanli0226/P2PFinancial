package com.bwei.net;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreate {
    public static NetApiService netApiService;
    public static NetApiService getNetApiService(){
        if (netApiService == null) {
            createApiService();
        }

        return netApiService;
    }

    private static void createApiService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(15000, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(build)
                .baseUrl(AppNetConfig.BASE_URL)
                .build();
        netApiService=retrofit.create(NetApiService.class);
    }
}
