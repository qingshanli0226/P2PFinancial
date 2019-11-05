package com.example.common.diyviews.presenter;

import com.example.network.DiyRetrofit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class DiyPresenter<T> implements InterPresenter<T>{
    private PresenterBaseView<T> baseView;
    @Override
    public void getData() {
        DiyRetrofit.getInterRetrofit()
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RequestBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //加载时显示等待
                        baseView.showLoadView();
                    }

                    @Override
                    public void onNext(RequestBody body) {
                        //完成隐藏
                        baseView.hindLoadView();

                    }

                    @Override
                    public void onError(Throwable e) {
                        baseView.hindLoadView();
                        baseView.setDataError("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setDataView(PresenterBaseView<T> presenterBaseView) {

    }

    @Override
    public void destoryDataView() {

    }
}
