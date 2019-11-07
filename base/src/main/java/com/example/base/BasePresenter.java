package com.example.base;

import android.util.Log;

import com.example.base.util.ErrorUtil;
import com.example.net.ResEnity;
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

public abstract class BasePresenter<T> implements IBsePresenter {
    private  IBaseView<T> iBaseView;

    @Override
    public void getData(String path) {
        Log.i("getData", "getData: ");


            RetrofitCreator.getNetInterence().getData(path)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            iBaseView.showLoading();
                            Log.i("onSubscribe", "onSubscribe: ");
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            iBaseView.hideLoading();
                            Log.i("ResponseBody", "onNext: -----------------");
                            Log.i("ResponseBody", "ResponseBody: "+responseBody.toString());
                            try {
                                if (isList()) {
                                    ResEnity<List<T>> o = new Gson().fromJson(responseBody.string(), getBeanType());
                                    if (o.getRet().equals("1")) {
                                        if (iBaseView != null) {
                                            iBaseView.onGetDataListSuccess(o.getData());
                                        }
                                    } else {
                                        //获取数据失败
                                        if (iBaseView != null) {
                                            iBaseView.onGetDataFailed("获取数据失败");
                                        }
                                    }
                                }else {
                                    T o = new Gson().fromJson(responseBody.string(), getBeanType());
                                    iBaseView.onGetDataSucces(o);
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                                throw  new RuntimeException("获取数据失败");
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            iBaseView.hideLoading();
                            String s = ErrorUtil.handleError(e);
                            if (iBaseView!=null){
                                iBaseView.onGetDataFailed(s);
                            }
                            Log.i("onError", "onError: ");
                        }

                        @Override
                        public void onComplete() {
                            Log.i("onComplete", "onComplete: ");
                        }
                    });

    }

    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView=iBaseView;
        Log.i("attachView", "attachView: ");
    }
    @Override
    public void detachView() {
          this.iBaseView=null;
    }
    public abstract Type getBeanType();

    public boolean isList(){
        return  false;
    }

}
