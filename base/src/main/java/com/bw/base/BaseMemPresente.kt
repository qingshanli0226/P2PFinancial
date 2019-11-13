package com.bw.base

import com.bw.net.RetrofitCreator
import java.lang.reflect.Type

abstract class BaseMemPresente<T> : IBasePresenter<T> {

    var iBaseView:IBaseView<T>? = null

    override fun doHttpRequest() {
//        RetrofitCreator().getApiService().getData(getHearerParmas(),getPath(),getParmas())
        RetrofitCreator().getApiService().getMyDate(getPath())
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