package com.example.net;

import com.example.commen.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 通过Retrofit进行网络请求
 */
public class RetrofitCreator {
    private static NetApiService netApiService;

    public static NetApiService getNetApiService() {
        if (netApiService == null) {
            createApiService();
        }

        return netApiService;
    }

    private static void createApiService() {
        //通过拦截器打印网络请求log
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //判断是否进行打印log
        if (Constant.PRINT_LOG) {  //true 打印
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else { //false 不打印
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        //定制OkHttpClient, 添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        //Retrofit的使用
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL) //基本网址
                .addConverterFactory(GsonConverterFactory.create()) //设置JSON转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava适配器
                .client(okHttpClient)
                .build();

        //创建网络请求接口的实例
        netApiService = retrofit.create(NetApiService.class);
    }


}
