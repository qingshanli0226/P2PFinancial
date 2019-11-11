package com.example.common.diyviews.presenter;

import com.example.common.diyviews.utils.AppErrorUtil;
import com.example.network.RetrofitUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public abstract class BasePresenter<T> implements IPresenter<T> {
    private IBaseView<T> baseView;
    //设置get请求或post请求
    @Override
    public void sendGetRequest() {
        Observable<ResponseBody> iRetrofit = RetrofitUtil.getIRetrofit().getGetData(setHead(), setUrlPath(), setParams());
        getData(iRetrofit);
    }

    @Override
    public void sendPostRequest() {
        Observable<ResponseBody> iRetrofit = RetrofitUtil.getIRetrofit().getPostData(setUrlPath(), setParams());
        getData(iRetrofit);
    }
    //请求数据并处理
    private void getData(Observable<ResponseBody> iRetrofit) {
        iRetrofit.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //加载时显示等待
                        baseView.showLoadView();
                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        //完成隐藏
                        baseView.hideLoadView();
                        if (isLists()){
                            //代码逻辑,扩展性,维护性
                        }else {
                            //忽略去壳操作
                            Gson gson = new Gson();
                            try {
                                T data = gson.fromJson(body.string(), getDataClass());
                                if (isTrueData()){
                                    baseView.getDataSuccess(data);
                                }else {
                                    //数据获取失败
                                    baseView.getDataError();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //收集错误 展示错误
                        AppErrorUtil.findError(e);
                        baseView.getDataError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //数据类  网址  头信息  参数信息
    protected abstract Type getDataClass();
    protected abstract String setUrlPath();
    protected HashMap<String,String> setHead(){
        return new HashMap<>();
    }
    protected HashMap<String,String> setParams(){
        return new HashMap<>();
    }

    private boolean isTrueData(){
        return true;
    }

    private boolean isLists(){
        return false;
    }

    @Override
    public void setIBaseView(IBaseView<T> presenterBaseView) {
        baseView=presenterBaseView;
    }

    @Override
    public void destoryIBaseView() {
        baseView=null;
    }
}
