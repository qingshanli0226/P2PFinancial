package com.example.net;

import com.example.base.BaseActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyActivity extends BaseActivity {
    @Override
    protected void initListener() {

    }

    @Override
    protected void initdata() {

        getdata();
    }

    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        FoodInterface foodInterface = retrofit.create(FoodInterface.class);
        foodInterface.getfood("1","10",1+"")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodeBean>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodeBean foodeBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getlayout() {
        return 0;
    }
}
