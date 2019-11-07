package com.example.base;

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
    public void getData() {
        RetrofitCreator.getNetInterence().getData(getHearerParms(),getpath(),getParms())
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
                          if (isList()){
                              try {
                                  ResEnity<List<T>> o = new Gson().fromJson(responseBody.string(), getBeanType());
                                  if (o.getRet().equals("1")){

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

    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView=iBaseView;
    }

    @Override
    public void detachView() {
          this.iBaseView=null;
    }
    public  abstract  String getpath();
    public HashMap<String,String> getParms(){
        return new HashMap<>();
    }
    public HashMap<String,String> getHearerParms(){
        return new HashMap<>();
    }
    public abstract Type getBeanType();

    public boolean isList(){
        return  false;
    }

}
