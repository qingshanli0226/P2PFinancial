package com.bwei.base;

import android.util.Log;

import com.bwei.net.RetrofitCreate;
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
    private IbaseView<T> ibaseView;
        public abstract Type getBeanType();//让子类来提供返回bean的类型

    //默认不是列表数据
    public boolean isList() { return false;}

    @Override
    public void getDate(String cat, HashMap params) {
        if (params!=null){
            RetrofitCreate.getNetApiService().getData(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseBody>() {
                @Override
                public void onSubscribe(Disposable d) {
//                    用户正在加载
                }

                @Override
                public void onNext(ResponseBody responseBody) {
                        try {
                            if (isList()){
                                List<T> resEntitylist= new Gson().fromJson(responseBody.string(), getBeanType());
//                              if (resEntitylist.getRet().equals("1")){
                                    if (ibaseView!=null){
                                        Log.i("ssss", "onNext: "+resEntitylist);
                                        ibaseView.onGetDataListSucess(resEntitylist);
                                    }
                                 else{
//                                    //获取数据失败
//                                    if (ibaseView!= null) {
                                        ibaseView.onGetDataFailed("获取数据失败");
                                    }

                            }else {
                                T resEntity = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (true) {
                                    //获取数据成功
                                    if (ibaseView!= null) {
                                        ibaseView.onGetDataSucess(resEntity);
                                    }
                                } else {
                                    //获取数据失败
                                    if (ibaseView!= null) {
                                        ibaseView.onGetDataFailed("获取数据失败");
                                    }
                                }
                        }
                        }catch (IOException e) {
                            throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理

                        }
                }

                @Override
                public void onError(Throwable e) {
//                    String errorMessage = ErrorUtil.handleError(e);
                    //获取数据失败
                    if (ibaseView!= null) {
                        ibaseView.onGetDataFailed("获取数据为空");
                    }
                   }

                @Override
                public void onComplete() {

                }
            });
        }else{
            Log.i("sss", "RetrofitCreate: 正在获取数据");
            RetrofitCreate.getNetApiService().getData(cat)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {

                            if (isList()){
                                try {
                                    Log.i("ssss", ": 获取集合dao数据"+responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else {

                                    try {
                                        Log.i("ssss", ": 获取dao数据"+responseBody.string());
                                        Gson gson = new Gson();
                                        T resEntity = gson.fromJson(responseBody.string(),getBeanType());
                                        Log.i("ssss", "onData: 获取的数据");
                                        Log.i("ssss", "onData: 获取的数据"+gson.fromJson(responseBody.string(), getBeanType()).toString());
                                        //获取数据成功
                                        if (ibaseView!= null) {
                                            Log.i("ssss", "ibaseViewSucess: ");
                                            ibaseView.onGetDataSucess(resEntity);
                                        } else {
                                            Log.i("ssss", "ibaseViewFailedFailed: ");
                                            //获取数据失败
                                            ibaseView.onGetDataFailed("获取数据失败");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void attachView(IbaseView ibaseView) {
        this.ibaseView=ibaseView;
    }

    @Override
    public void datachView() {
        this.ibaseView=null;
    }
}
