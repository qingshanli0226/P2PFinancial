package com.bw.base

import com.bw.common.P2PError

interface IBaseView<T> {

    fun onHttpRequestDataSuccess(requestCode:Int,data:T)
    fun onHttpRequestDataListSuccess(requestCode: Int,data:List<T>)
    fun onHttpRequestDataFailed(requestCode: Int,error: P2PError)

}