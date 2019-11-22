package com.bw.base

//获取数据，各个页面都可以调用该接口获取数据
interface IBasePresenter<T> {

    //来区分不同的网络请求
    fun doHttpRequest()
    fun doHttpPostRequest()
    fun attachView(iBaseView:IBaseView<T>)
    fun datachView()

}