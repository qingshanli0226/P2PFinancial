package com.bw.net

import com.bw.common.Constant
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

    private fun createApiService() {
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

        var retrofit:Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//确保service方法，返回值是Observable
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)   //okhttpclient
            .baseUrl(Constant().BASE_URL)   //base url
            .build()

        netApiService = retrofit.create(NetApiService::class.java)

    }

}