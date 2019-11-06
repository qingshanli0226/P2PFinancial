package jni.example.net;

import java.util.concurrent.TimeUnit;

import jni.example.common.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator implements IModel {
    public static NetApiService netApiService;

    public static NetApiService getNetApiService() {
        if (netApiService == null) {
            createApiService();
        }
        return netApiService;
    }

    private static void createApiService() {
        //通过拦截器打印网络请求log
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //可以设置打印级别
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
                .client(okHttpClient)
                .baseUrl(Constant.BASE_URL) //base url
                .build();

        netApiService = retrofit.create(NetApiService.class);
    }

    @Override
    public void destroy() {
        if(netApiService!=null)
            netApiService = null;
    }
}
