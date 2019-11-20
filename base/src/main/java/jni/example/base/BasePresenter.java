package jni.example.base;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jni.example.common.ConstantMain;
import jni.example.net.ErrorUtil;
import jni.example.net.RetrofitCreator;
import okhttp3.ResponseBody;

public abstract class BasePresenter<T> implements IPresenter {
    protected IView<T> iView;
    public IGetDateListener listener;

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //TODO 如果没有页面，说明是服务请求数据
                        if (iView != null) {
                            //提示用户正在加载，显示加载页
                            iView.showLoading();
                        }else{

                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (iView != null) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    iView.hideLoading();
                                }
                            }, 2000);
                            try {
                                //如果返回的数据是列表
                                if (isList()) {
                                    List<T> dateList = new Gson().fromJson(responseBody.string(), getBeanType());
                                    iView.onGetDataListSuccess(dateList);

                                } else {
                                    T data = new Gson().fromJson(responseBody.string(), getBeanType());
                                    iView.onGetDataSuccess(data);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                            }
                        } else {
                            try {
                                //如果返回的数据是列表
                                if (isList()) {
                                    List<T> dateList = new Gson().fromJson(responseBody.string(), getBeanType());
                                    listener.onGetDataListSuccess(dateList);
                                } else {
                                    T data = new Gson().fromJson(responseBody.string(), getBeanType());
                                    listener.onGetDataSuccess(ConstantMain.INDEX,data);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String errorMessage = ErrorUtil.handleError(e);
                        if (iView != null) {
                            iView.hideLoading();
                            iView.showErrorPage();

                            //获取数据失败
                            iView.onGetDataFailed(errorMessage);
                        }else{
                            listener.onGetDataFailed(errorMessage);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void postData() {
        RetrofitCreator.getNetApiService().postData(getHearerParmas(), getPath(), getParmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            T resEntity = new Gson().fromJson(responseBody.string(), getBeanType());
                            Log.i("lhf--postNext", "onNext2: "+responseBody.string());
                            //获取数据成功
                            if (iView!= null) {
                                iView.onPostDataSuccess(resEntity);
                            }

                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //获取数据失败
                        if (iView!= null) {
                            iView.onPostDataFailed(ErrorUtil.handleError(e));
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

    public void attachListener(IGetDateListener listener){
        this.listener = listener;
    }

    public void detachListener(){
        if(listener!=null){
            listener = null;
        }
    }
}
