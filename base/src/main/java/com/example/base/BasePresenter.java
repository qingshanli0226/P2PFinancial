package com.example.base;

import android.os.Handler;
import android.util.Log;

import com.example.base.util.ErrorUtil;
import com.example.common.P2PError;
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



            RetrofitCreator.getNetInterence().getData(getHearerParmas(),path,getParmas())
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
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    iBaseView.hideLoading();
                                }
                            },1000);


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
                            Log.i("onError", "onError: ");
                            P2PError error = ErrorUtil.handleError(e);
                            if (iBaseView!=null){
                                iBaseView.onGetDataFailed(error.getErrorMessage());
                            }

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
    public HashMap<String, String> getParmas() {
        return new HashMap<>();
    }//让子类来提供调用网络请求 的参数
    public HashMap<String, String> getHearerParmas(){
        return new HashMap<>();
    }
}
