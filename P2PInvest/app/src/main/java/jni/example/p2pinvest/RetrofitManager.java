package jni.example.p2pinvest;

import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitManager {

    private RetrofitManager(){}

    private static RetrofitManager manager = null;

    public static RetrofitManager getInstance(){
        if (manager==null)
            manager = new RetrofitManager();
        return manager;
    }

}
