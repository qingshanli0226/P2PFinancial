package com.bw.base

import com.bw.base.utils.ErrorUtil
import com.bw.base.utils.P2PError
import com.bw.common.AppNetConfig
import com.bw.net.ResEntity
import com.bw.net.RetrofitCreator
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.reflect.Type


//mvp的presenter的抽象类，实现获取网络数据的业务逻辑
abstract class BasePresenter<T> : IBasePresenter<T> {

    var iBaseView:IBaseView<T>? = null

    //http get网络请求
    override fun doHttpRequest() {
        RetrofitCreator().getApiService().getMyDate(getPath())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    //提示用户正在加载，显示加载页
                    iBaseView?.showLoading()
                }

                override fun onNext(t: ResponseBody) {
                    //关闭显示的加载页面
                    iBaseView?.hideLoading()
                    try {
                        //如果返回的数据是列表
                        if (isList()){
                            var bean:List<T> = Gson().fromJson(t.string(),getBeanType())
                            if (iBaseView != null){
                                //获取列表数据成功
                                iBaseView?.onHttpRequestDataListSuccess(bean)
                                iBaseView?.hideLoading()
                            }
//                            else{
//                                //获取列表数据失败
//                                iBaseView?.onHttpRequestDataFailed(P2PError.BUSINESS_ERROR)
//                            }
                        }else{
                            var bean:T = Gson().fromJson(t.string(),getBeanType())
                            if (iBaseView != null){
                                //获取数据成功
                                iBaseView?.onHttpRequestDataSuccess(100,bean)
                                iBaseView?.hideLoading()
                            }
//                            else{
//                                //获取数据失败
//                                iBaseView?.onHttpRequestDataFailed(P2PError.BUSINESS_ERROR)
//                            }
                        }
                    }catch (e:IOException){
                        //抛出异常，让onError函数统一处理
                        throw RuntimeException("获取数据为空")
                        e.printStackTrace()
                    }
                }

                override fun onError(e: Throwable) {
                    val handleError = ErrorUtil.handleError(e)
                    if (iBaseView != null){
                        iBaseView?.onHttpRequestDataFailed(handleError)
                    }
//                    //关闭显示的加载页面
//                    iBaseView?.hideLoading()
//                    //获取数据失败
//                    iBaseView?.onHttpRequestDataFailed(ErrorUtil.handleError(e))
                }

            })

    }

    override fun doHttpPostRequest() {

    }

    override fun attachView(iBaseView: IBaseView<T>) {
        this.iBaseView = iBaseView
    }

    override fun datachView() {
        this.iBaseView = null
    }

    //让子类提供获取网络数据的路径
    abstract fun getPath() : String

    fun getName() : HashMap<String,String>{
        return HashMap<String,String>()
    }

    //让子类来提供调用网络请求的参数
    fun getPassWord() : HashMap<String,String>{
        return HashMap<String,String>()
    }

    fun getPhone() : HashMap<String,String>{
        return HashMap()
    }

    //让子类来提供调用网络请求的头参数，例如token
    //让子类来提供返回bean的类型
    abstract fun getBeanType() : Type

    //默认不是列表数据
    open fun isList() : Boolean{
        return false
    }

}