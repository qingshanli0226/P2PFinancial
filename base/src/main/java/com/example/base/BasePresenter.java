package com.example.base;

import android.media.session.MediaSession;
import android.util.Log;

import com.example.net.Constant;
import com.example.net.MainEntity;
import com.example.net.ResEntity;
import com.example.net.RetrofitCreator;
import com.example.net.util.ErrorUtil;
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
    private String baseUrl;

    @Override
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    @Override
    public void getBannerImg() {
        RetrofitCreator.getNetApiService(baseUrl).getData(getHeadMap(), getPath(), getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            if (isList()) {
                                MainEntity<List<T>> resEntity = new Gson().fromJson(responseBody.string(), getType());
                                if (iBaseView != null) {
                                    iBaseView.onGetDataListSucess(resEntity.getData());
                                }
                            } else {
                                MainEntity<T> resEntity = new Gson().fromJson(responseBody.string(), getType());
                                if (iBaseView != null) {
                                    iBaseView.onGetDataSucess(resEntity.getData());
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("####",e.toString());
//                        String s = ErrorUtil.INSTANCE.handleError(e);
//                        if (iBaseView != null) {
//                            iBaseView.onGetDataFailed(s);
//                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData() {
        RetrofitCreator.getNetApiService(baseUrl).getData(getHeadMap(), getPath(), getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            if (isList()) {
                                ResEntity<List<T>> resEntityList = new Gson().fromJson(responseBody.string(), getType());
                                if (resEntityList.getRet().endsWith("1")) {
                                    if (iBaseView != null) {
                                        iBaseView.onGetDataListSucess(resEntityList.getData());
                                    }
                                } else {
                                    if (iBaseView != null) {
                                        iBaseView.onGetDataFailed("获取数据失败");
                                    }
                                }
                            } else {
                                ResEntity<T> resEntity = new Gson().fromJson(responseBody.string(), getType());
                                if (iBaseView != null) {
                                    if (resEntity.getRet().endsWith("1")) {
                                        iBaseView.onGetDataSucess(resEntity.getData());
                                    } else {
                                        if (iBaseView != null) {
                                            iBaseView.onGetDataFailed("获取数据失败");
                                        }
                                    }
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = ErrorUtil.INSTANCE.handleError(e);
                        if (iBaseView != null) {
                            iBaseView.onGetDataFailed(s);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public HashMap<String, String> getQueryMap() {
        return new HashMap<>();
    }

    public abstract String getPath();

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

    public abstract Type getType();

    public boolean isList() {
        return false;
    }
}
