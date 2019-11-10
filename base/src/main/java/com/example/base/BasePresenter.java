package com.example.base;

import android.util.Log;

import com.example.base.util.ErrorUtil;
import com.example.net.Constant;
import com.example.net.ResEntity;
import com.example.net.RetrofitCreator;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public abstract class BasePresenter<T> implements IBasePresenter {

    private IBaseView<T> iBaseView;

    @Override
    public void getBannerImg(final int requestCode) {
        RetrofitCreator.getNetApiService(Constant.BASE_URL).getData(getHeadMap(), getPath(), getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        iBaseView.onLoading();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            T resEntity = new Gson().fromJson(responseBody.string(), getType());
                            if (iBaseView != null) {
                                iBaseView.onGetDataSucess(requestCode, resEntity);
                                iBaseView.onStopLoading();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBaseView != null) {
                            Log.e("####", "网络请求失败");
                            iBaseView.onGetDataFailed(requestCode, ErrorUtil.handleError(e));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getAllInest(final int requestCode) {
        RetrofitCreator.getNetApiService(Constant.BASE_URL).getData(getHeadMap(), getPath(), getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        iBaseView.onLoading();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            ResEntity<List<T>> resEntity = new Gson().fromJson(responseBody.string(), getType());
                            if (iBaseView != null) {
                                iBaseView.onGetDataListSucess(requestCode, resEntity.getData());
                                iBaseView.onStopLoading();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBaseView != null) {
                            iBaseView.onGetDataFailed(requestCode, ErrorUtil.handleError(e));
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    //请求头参数
    public HashMap<String, String> getQueryMap() {
        return new HashMap<>();
    }

    //必传参数
    public abstract String getPath();

    //可变参数
    public HashMap<String, String> getHeadMap() {
        return new HashMap<>();
    }

    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void detachView() {
        this.iBaseView = null;
    }

    //请求数据类型
    public abstract Type getType();
}
