package com.bw.base

import com.bw.base.utils.P2PError

interface IBaseView<T> {

    fun onHttpRequestDataSuccess(requestCode:Int,data:T)
    //返回列表的回调
    fun onHttpRequestDataListSuccess(requestCode: Int,data:List<T>)
    fun onHttpRequestDataFailed(requestCode: Int,error: P2PError)
    //开始请求数据时，开启显示加载页面
    fun showLoading(requestCode:Int)
    //请求数据结束是，关闭显示加载页面
    fun hideLoading(requestCode: Int)

}