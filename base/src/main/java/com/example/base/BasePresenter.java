package com.example.base;

import android.util.Log;

import com.example.base.utils.ErrorUtil;
import com.example.net.ResEntity;
import com.example.net.RetrofitCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public abstract class BasePresenter<T> implements IBasePresenter {

    private IBaseView<T> iBaseView;

    @Override
    public void getData() {
        RetrofitCreator.getApiService().getData(getHearerParmas(), getPath(), getParmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        iBaseView.showLoading();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        iBaseView.hideLoading();
                        try {
                            if (isList()) {

                                       List<T> list = new GsonBuilder().create().fromJson(responseBody.string(), getBeanType());

                                       Log.e("####",list.toString());
                                       if(list!=null){
                                            if (iBaseView!= null) {
                                                iBaseView.onGetDataListSuccess(list);
                                            }
                                        }else {
                                            iBaseView.onGetDataFailed("获取数据失败");
                                        }
                            } else {
                                T t = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (t!=null) {
                                    if (iBaseView!= null) {
                                        iBaseView.onGetDataSuccess(t);
                                    }
                                } else {
                                    if (iBaseView!= null) {
                                        iBaseView.onGetDataFailed("获取数据失败");
                                    }
                                }
                            }

                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseView.hideLoading();
                        String errorMessage = ErrorUtil.handleError(e);
                        //获取数据失败
                        if (iBaseView!= null) {
                            iBaseView.onGetDataFailed(errorMessage);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void detachView() {
        this.iBaseView = null;
    }

    public abstract String getPath();
    public HashMap<String, String> getParmas() {
        return new HashMap<>();
    }
    public HashMap<String, String> getHearerParmas(){
        return new HashMap<>();
    }

    public abstract Type getBeanType();

    public boolean isList() {
        return false;
    }

}
