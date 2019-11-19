package com.example.p2pdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.common.AppNetWork;
import com.example.net.RetrofitCreator;
import com.example.p2pdemo.Bean.UpdateBean;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class UpdateService extends Service {

    private IApkListener iApkListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public void RegisterApkListener(IApkListener iApkListener) {
        this.iApkListener = iApkListener;
    }

    public void getUpdateApk() {
        RetrofitCreator.getNetApiService().getMyData(AppNetWork.UPDATE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            UpdateBean toJson = (UpdateBean) new Gson().fromJson(responseBody.string(), UpdateBean.class);
                            iApkListener.getApkUpdate(toJson);
                        } catch (IOException e) {

                            e.printStackTrace();
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

    public interface IApkListener {
        void getApkUpdate(UpdateBean updateBean);
    }

    class MyBind extends Binder {
        public UpdateService getServer() {
            return UpdateService.this;
        }
    }

}
