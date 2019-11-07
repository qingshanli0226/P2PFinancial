package com.example.base;

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

public abstract class BasePresenter<T> implements IBasePresenter {
    public  IBaseView<T> iBaseView;
    @Override
    public void getData() {
        RetrofitCreator.getNetApiService().getData(getHearerParmas(),getpath(),getparmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载,显示加载页
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //如果返回的数据是列表在这个里面执行

                        if (isList()){
                            try {
                                ResEntity<List<T>> resEntityList=new Gson().fromJson(responseBody.string(), getBeanType());
                                if (resEntityList.getRet().equals("1")){
                                    //获取列表数据成功
                                    if (iBaseView!=null){
                                        iBaseView.onGetDataListSucess(resEntityList.getData());
                                    }
                                }else {
                                    //获取数据失败
                                    if (iBaseView!=null){
                                        iBaseView.onGetDataFailed("获取数据失败");

                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                ResEntity<T> resEntity=new Gson().fromJson(responseBody.string(),getBeanType());
                                if (resEntity.getRet().equals("1")){
                                    //获取数据成功
                                    if (iBaseView!=null){
                                        iBaseView.onGetDataSucess(resEntity.getData());
                                    }
                                }else {
                                    //获取数据失败
                                    if (iBaseView!=null){
                                        iBaseView.onGetDataFailed("获取数据失败");
                                    }
                                }
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

                    }
                });
    }

    @Override
    public void attachView(IBaseView ibaseView) {
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

    public abstract HashMap<String,String> getParmas();

    //让子类来提供返回的Bean的类型
    public abstract Type getBeanType();

    public abstract void attachView(IBasePresenter ibaseView);

    //默认不是列表数据
    public boolean isList(){
        return false;
    }

}
