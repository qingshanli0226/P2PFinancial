package jni.example.base;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jni.example.net.ErrorUtil;
import jni.example.net.ResEntity;
import jni.example.net.RetrofitCreator;
import okhttp3.ResponseBody;

public abstract class BasePresenter<T> implements IPresenter {
    protected IView<T> iView;

    public abstract String getPath();//让子类提供获取网络数据的路径

    public HashMap<String, String> getParmas() {
        return new HashMap<>();
    }//让子类来提供调用网络请求 的参数

    public HashMap<String, String> getHearerParmas() {
        return new HashMap<>();
    }//让子类来提供调用网络请求的头参数, 例如token

    public abstract Type getBeanType();//让子类来提供返回bean的类型

    //默认不是列表数据
    public boolean isList() {
        return false;
    }

    @Override
    public void getData() {
        RetrofitCreator.getNetApiService().getData(getHearerParmas(), getPath(), getParmas())
                .subscribeOn(Schedulers.io())   //子
                .observeOn(AndroidSchedulers.mainThread())  //主

                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载，显示加载页
                        iView.showLoading();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iView.hideLoading();
                            }
                        },10000);
                        try {
                            //如果返回的数据是列表
                            if (isList()) {
                                List<T> dateList = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (iView != null) {
                                    iView.onGetDataListSuccess(dateList);
                                }
                            } else {
                                T data = new Gson().fromJson(responseBody.string(), getBeanType());

                                if (iView != null) {
                                    iView.onGetDataSuccess(data);
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading();
                        iView.showErrorPage();
                        String errorMessage = ErrorUtil.handleError(e);
                        //获取数据失败
                        if (iView != null) {
                            iView.onGetDataFailed(errorMessage);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(IView iView) {
        this.iView = iView;
    }

    @Override
    public void detachView() {
        if (iView != null)
            iView = null;
    }
}
