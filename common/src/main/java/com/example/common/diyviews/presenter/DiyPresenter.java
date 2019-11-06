package com.example.common.diyviews.presenter;

import com.example.network.APPErrorUtils;
import com.example.network.DiyRetrofit;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public abstract class DiyPresenter<T> implements InterPresenter<T>{
    private PresenterBaseView<T> baseView;
    @Override
    public void getData() {
        DiyRetrofit.getInterRetrofit()
                .getData()
                .subscribeOn(Schedulers.io())
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
                        baseView.hindLoadView();
                        Gson gson = new Gson();
                        try {
                            T data = gson.fromJson(body.string(), getDataClass());
                            baseView.setDataSuccess(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        APPErrorUtils.findError(e);
                        baseView.hindLoadView();
                        baseView.findError();
                        baseView.setDataError("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected abstract Type getDataClass();

    @Override
    public void setDataView(PresenterBaseView<T> presenterBaseView) {
        baseView=presenterBaseView;
    }

    @Override
    public void destoryDataView() {

    }
}
