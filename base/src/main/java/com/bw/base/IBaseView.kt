package com.bw.base

interface IBaseView<T> {

    fun onHttpRequestDataSuccess(requestCode:Int,data:T)
    //返回列表的回调
    fun onHttpRequestDataListSuccess(data:List<T>)
    fun onHttpRequestDataFailed(fileMess:String)
    //开始请求数据时，开启显示加载页面
    fun showLoading()
    //请求数据结束是，关闭显示加载页面
    fun hideLoading()

}