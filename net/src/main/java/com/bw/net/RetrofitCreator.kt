package com.bw.net

import com.bw.common.AppNetConfig
import com.bw.common.Constant
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCreator {

    var netApiService:NetApiService? = null

    fun getApiService() : NetApiService{
        if (netApiService == null){
            createApiService()
        }
        return netApiService!!
    }

    fun createApiService() : NetApiService {
        //通过拦截器打印网络请求log
        var loggingInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor()

        //可以设置打印级别
        if (Constant().PRINT_LOG){
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        var okHttpClient:OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(50,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        var retrofit:Retrofit.Builder = Retrofit.Builder()
        var retrofitBuild:Retrofit = retrofit
            .baseUrl(AppNetConfig.BASE_URL)   //base url
            .client(okHttpClient)   //okhttpclient
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//确保service方法，返回值是Observable
            .build()

        netApiService = retrofitBuild.create(NetApiService::class.java)

        return netApiService!!

    }

}