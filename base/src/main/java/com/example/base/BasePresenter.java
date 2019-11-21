package com.example.base;

import android.os.Handler;

import com.example.base.util.ErrorUtil;
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
//mvp的presenter的抽象类,实现获取网络数据的业务逻辑
public abstract class BasePresenter<T> implements IBasePresenter<T> {
    int i=0;
    public  IBaseView<T> iBaseView;

    //Get的网络请求
    @Override
    public void ongetHttp() {

        RetrofitCreator.getNetApiService().getData(getHearerParmas(),getpath(),getparmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载,显示加载页
                        if (i==0){
                            iBaseView.onShow(200);
                            i++;
                        }


                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //如果返回的数据是列表在这个里面执行
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iBaseView.onShow(300);
                            }
                        },3000);
                        if (isList()){
                            try {
                                List<T> resEntityList=new Gson().fromJson(responseBody.string(), getBeanType());

                                        iBaseView.onGetDataListSucess(resEntityList);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                T resEntity=new Gson().fromJson(responseBody.string(),getBeanType());

                                iBaseView.onGetDataSucess(resEntity);

                            } catch (IOException e) {
                                throw new RuntimeException("获取数据为空");

                            }
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        String errorMessage=ErrorUtil.hanleError(e);

                        //获取数据失败
                        if (iBaseView!=null){
                            iBaseView.onGetDataFailed(errorMessage);

                        }
                    }

                    @Override
                    public void onComplete() {
                        //加载完成时


                    }
                });
    }

    //POST的网络请求
    public void doHttpPostRequest(final int requestCode){
        RetrofitCreator.getNetApiService().getData(getHearerParmas(),getpath(),getparmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载POST请求的加载

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            T resEntity = new Gson().fromJson(responseBody.string(), getBeanType());

                            //获取数据成功
                            if (iBaseView!=null){
                                iBaseView.onHttpRequestDataSuccess(requestCode,resEntity);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBaseView!=null){
                            iBaseView.onHttpRequestDataFailed(requestCode);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(IBaseView<T> ibaseView) {
        this.iBaseView=ibaseView;
    }

    @Override
    public void detachView() {
        this.iBaseView =null;
    }
    //子类提供获取数据的路径
    public abstract String getpath();

    //子类提供网络请求的参数
    public HashMap<String,String>getparmas(){
        return new HashMap<>();
    }
    //让子类提供调用网络请求的头参数
    public HashMap<String,String> getHearerParmas() {
        return new HashMap<>();
    }

    public  HashMap<String,String> getParmas(){
        return    new HashMap<>();
    }

    //让子类来提供返回的Bean的类型
    public abstract Type getBeanType();

    public abstract void attachView(IBasePresenter ibaseView);

    //默认不是列表数据
    public boolean isList(){
        return false;
    }


}
