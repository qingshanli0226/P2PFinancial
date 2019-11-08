package com.example.base;

import android.os.Handler;
import android.util.Log;

import com.example.base.utils.ErroUtils;

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

//mvp的抽象类,实现获取网路数据的业务逻辑
public abstract class BasePresenter<T> implements IBasePresenter{


    private IBaseView<T> iBaseView;

    @Override
    public void getInvestData() {
        RetrofitCreator.getNetApiService()
                .getMyData(getPath())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        iBaseView.loadView();

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.e("##","99");
                        if(isList()){
                            Log.e("##","55");
                            try {
                                Log.e("##",responseBody.toString());
                                List<T> bean= new Gson().fromJson(responseBody.string(), getBeanType());
                                Log.e("##",""+bean.toString());
                                if(iBaseView!=null){
                                    iBaseView.onGetDataListSucess(bean);
                                    iBaseView.unLoadView();
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("数据为空!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseView.unLoadView();
                        if(iBaseView==null){
                            iBaseView.onGetDataFiled(ErroUtils.handlerError(e));
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData() {

        RetrofitCreator.getNetApiService().getMyData(getPath())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            //提示用户正在加载,显示加载页
                    iBaseView.loadView();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if(isList()){
                    try {
                        T bean = new Gson().fromJson(responseBody.string(), getBeanType());
                        //获取数据成功
                        if(iBaseView!=null){
                            iBaseView.onGetDataSucess(bean);
                            iBaseView.unLoadView();
                        }

                    } catch (Exception e){

                       throw  new RuntimeException("数据为空!");
                    }
                }


            }

            @Override
            public void onError(Throwable e) {
                iBaseView.unLoadView();
                String ErrorString = ErroUtils.handlerError(e);
                if(iBaseView!=null){
                    iBaseView.onGetDataFiled(ErrorString);
                }


            }

            @Override
            public void onComplete() {

            }
        });


    }



    public void attachView(IBaseView view){
        this.iBaseView=view;
    }
    //销毁页面


    @Override
    public void detchView() {
        this.iBaseView=null;
    }

    public abstract String getPath();//获取子类获取网络路劲

    public  HashMap<String,String> getParams(){
        return new HashMap<String,String>();
    }//子类提供网络请求的参数

    public  HashMap<String,String> getHeaderParms(){
        return new HashMap<String,String>();
    }//让子类来提供网络的头参数

    public abstract Type getBeanType();//让子类提供放回Bean的类型

    //默认不是列表数据
   public  boolean isList(){
        return false;
    }




}
