package com.bw.base

import com.bw.base.utils.ErrorUtil
import com.bw.base.utils.P2PError
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
    override fun doHttpRequest(requestCode: Int) {
        RetrofitCreator().getApiService().getData(getHearerParmas(),getPath(),getParmas())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    //提示用户正在加载，显示加载页
                    iBaseView?.showLoading(requestCode)
                }

                override fun onNext(t: ResponseBody) {
                    //关闭显示的加载页面
                    iBaseView?.hideLoading(requestCode)
                    try {
                        //如果返回的数据是列表
                        if (isList()){
                            var resEntity:ResEntity<List<T>> = Gson().fromJson(t.string(),getBeanTyper())
                            if (resEntity.ret == "1"){
                                //获取列表数据成功
                                iBaseView?.onHttpRequestDataListSuccess(requestCode,resEntity.data)
                            }else{
                                //获取列表数据失败
                                iBaseView?.onHttpRequestDataFailed(requestCode,P2PError.BUSINESS_ERROR)
                            }
                        }else{
                            var resEntity:ResEntity<T> = Gson().fromJson(t.string(),getBeanTyper())
                            if (resEntity.ret == "1"){
                                //获取数据成功
                                iBaseView?.onHttpRequestDataSuccess(requestCode,resEntity.data)
                            }else{
                                //获取数据失败
                                iBaseView?.onHttpRequestDataFailed(requestCode,P2PError.BUSINESS_ERROR)
                            }
                        }
                    }catch (e:IOException){
                        //抛出异常，让onError函数统一处理
                        throw RuntimeException("获取数据为空")
                        e.printStackTrace()
                    }
                }

                override fun onError(e: Throwable) {
                    //关闭显示的加载页面
                    iBaseView?.hideLoading(requestCode)
                    //获取数据失败
                    iBaseView?.onHttpRequestDataFailed(requestCode,ErrorUtil.handleError(e))
                }

            })

    }

    override fun attachView(iBaseView: IBaseView<T>) {
        this.iBaseView = iBaseView
    }

    override fun datachView() {

    }

    //让子类提供获取网络数据的路径
    abstract fun getPath() : String

    fun getParmas() : HashMap<String,String>{
        return HashMap()
    }

    //让子类来提供调用网络请求的参数
    fun getHearerParmas() : HashMap<String,String>{
        return HashMap()
    }

    //让子类来提供调用网络请求的头参数，例如token
    //让子类来提供返回bean的类型
    abstract fun getBeanTyper() : Type

    //默认不是列表数据
    fun isList() : Boolean{
        return false
    }

}