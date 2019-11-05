package com.example.base.presenter;

import com.example.base.view.IBaseView;
import com.example.commen.util.ErrorUtil;
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

/**
 * mvp的 P层抽象类,  实现网络数据的业务逻辑
 */
public abstract class BasePresenter<T> implements IBasePresenter {

    private IBaseView<T> iBaseView;

    @Override
    public void getData() {
        RetrofitCreator.getNetApiService().getData(getHeaderParams(), getPath(), getParams())
                .subscribeOn(Schedulers.io()) //订阅
                .observeOn(AndroidSchedulers.mainThread()) //观察
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载, 显示加载页
                        iBaseView.showLoading();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //隐藏加载页
                        iBaseView.hideLoading();

                        try {
                            //判断数据是否是列表
                            if (isList()) {
                                List<T> resEntityList = new Gson().fromJson(responseBody.string(), getBeanType());
                                //TODO 为判断网络数据是否请求成功, 默认成功
                                if (iBaseView != null) {
                                    iBaseView.onGetDataListSuccess(resEntityList);
                                }
                            } else {
                                T resEntity = new Gson().fromJson(responseBody.string(), getBeanType());
                                ;
                                if (iBaseView != null) {
                                    iBaseView.onGetDataSuccess(resEntity);
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空"); //扔出异常,让onError函数统一处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //获取数据失败
                        iBaseView.hideLoading();

                        String errorMessage = ErrorUtil.handleError(e);

                        //获取数据失败
                        if (iBaseView != null) {
                            iBaseView.onGetDataFailed(errorMessage);
                        }
                    }

                    @Override
                    public void onComplete() {
                        iBaseView.hideLoading();
                    }
                });

    }


    //    protected abstract Class<Object> getBeanType();
    //设置bean的类型
    protected abstract Type getBeanType();

    //设置返回的数据是否是列表
    protected boolean isList() {
        return false;
    }


    //让子类提供获取网络数据的路径
    protected abstract String getPath();

    //让子类提供调用网络请求的头参数, 例如token
    protected HashMap<String, String> getHeaderParams() {
        return new HashMap<>();
    }

    //让子类来提供调用网络请求 的参数
    protected HashMap<String, String> getParams() {
        return new HashMap<>();
    }


    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void detachView() {
        if (iBaseView != null) {
            this.iBaseView = null;
        }
    }
}
