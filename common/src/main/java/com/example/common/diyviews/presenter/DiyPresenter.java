package com.example.common.diyviews.presenter;

import com.example.network.AppErrorUtil;
import com.example.network.DiyRetrofit;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public abstract class DiyPresenter<T> implements InterfacePresenter<T> {
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
                        if (isLists()){
                            //代码逻辑,扩展性,维护性
                        }else {
                            //忽略去壳操作
                            Gson gson = new Gson();
                            try {
                                T data = gson.fromJson(body.string(), getDataClass());
                                if (isTrueData()){
                                    baseView.setDataSuccess(data);
                                }else {
                                    //数据获取失败  放入备用数据
                                    baseView.setDataError(data.toString());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //收集错误   隐藏加载页  显示错误页  显示备用数据
                        AppErrorUtil.findError(e);
                        baseView.hindLoadView();
                        baseView.findError();
                        baseView.setDataError("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private boolean isTrueData(){
        return true;
    }

    private boolean isLists(){
        return false;
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
