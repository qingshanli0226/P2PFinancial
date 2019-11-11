package com.bwei.base;

import android.util.Log;

import com.bwei.base.bean.ErrorUitl;
import com.bwei.common.P2PError;
import com.bwei.net.RetrofitCreate;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

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
    public HashMap<String, String> getParmas() {
        return new HashMap<>();
    }//让子类来提供调用网络请求 的参数
    public abstract String getPath();
    public HashMap<String, String> getHearerParmas(){
        return new HashMap<>();
    }

    @Override
    public void getDate() {
            Log.i("sss", "RetrofitCreate: 正在获取数据");
            RetrofitCreate.getNetApiService().getData(getHearerParmas(),getPath(),getParmas())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                            ibaseView.showLoading();
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            ibaseView.hideLoading(2);
                            if (isList()){
                                try {
                                    Log.i("ssss", ": 获取集合dao数据"+responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                    try {
                                        Gson gson = new Gson();
                                        String json = responseBody.string();
                                        Log.i("ssss", ": 获取dao数据"+json);

                                        T resEntity = gson.fromJson(json,getBeanType());
                                        //获取数据成功
                                        if (ibaseView!= null) {
                                            Log.i("ssss", "ibaseViewSucess: "+resEntity.toString());
                                            ibaseView.onGetDataSucess(resEntity);
                                        } else {
                                            Log.i("ssss", "ibaseViewFailedFailed: ");
                                            //获取数据失败
                                            ibaseView.onHttpRequestDataFailed(4001,P2PError.BUTINESS_ERROR);

                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException("获取数据为空");
                                    }
                                }
                        }

                        @Override
                        public void onError(Throwable e) {
//                            显示错误页面
                            ibaseView.hideLoading(1);
                            ibaseView.onHttpRequestDataFailed(10000, ErrorManager.handleError(e));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
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
