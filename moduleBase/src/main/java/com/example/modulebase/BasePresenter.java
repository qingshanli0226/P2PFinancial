package com.example.modulebase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.example.modulenet.RetrofigCreator;

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

        //get网络请求

    @Override
    public void doHttpRequest(int requestCode) {
        RetrofigCreator.getNetApiService().getData(getHearerParmas(),getPath(),getParmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>(){

                    @Override
                    public void onSubscribe(Disposable d) {
                        iBaseView.showLoading(requestCode);//在没有加载完数据时显示加载页面提示用户
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //数据加载完了关闭加载页面
                        iBaseView.hideLoading(requestCode);
                      try {
                          //如果返回的数据是列表
                          if (isList()) {
                              //解析数据
                              List<T> dataList = JSON.parseObject(responseBody.string(), getBeanType());
                              if (iBaseView != null)
                                  iBaseView.onLoadDataListSuccess(requestCode, dataList);
                          }else {
                              //返回的是普通数据
                              T dataList2 = JSON.parseObject(responseBody.string(), getBeanType());
                              if (iBaseView != null)
                                  iBaseView.onLoadDataSuccess(requestCode,dataList2);
                          }
                      }catch (IOException e){
                          throw new RuntimeException("网络数据为空");//扔出异常让onError函数统一处理
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



    @Override
    public void doHttpPostRequest(int requestCode) {

    }

    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView=iBaseView;
    }

    @Override
    public void detachView() {

    }
    //子类提供解析类型
    public boolean isList() {return false;}
    //子类提供解析的bean类型
    protected abstract Type getBeanType();
    //子类提供解析的网址中间部分
    protected abstract String getPath();
    //子类提供网络数据的参数
    public HashMap<String,String> getParmas(){return new HashMap<>();}
    //子类提供的请求头参数
    public HashMap<String,String> getHearerParmas(){ return new HashMap<>();}
}
