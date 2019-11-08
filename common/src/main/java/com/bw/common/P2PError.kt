package com.bw.common

//使用枚举类来定义错误
enum class P2PError(i: Int, s: String) {
    //网络相关错误，从1000开始
    HTTP_ERROR(1001,"网络错误"),
    HTTP_SOCKET_TIME_OUT_ERROR(1002,"网络连接超时错误"),
    //解析相关错误，从2000开始
    JSON_ERROR(2001,"数据格式不对"),
    //文件相关错误,从3000开始
    FILE_OPEN_ERROR(3001,"打开文件错误"),
    //业务错误，从4000开始
    BUSINESS_ERROR(4001,"获取数据错误"),
    //内存错误，从5000开始
    MEN_ERROR(5001,"内存处理错误"),
    OTHER_ERROR(10000,"其它错误");

    var errorMessage:String? = null
    var errorCode:Int? = null

//    fun P2PError(errorCode:Int,errorMessage:String){
//        this.errorMessage = errorMessage
//        this.errorCode = errorCode
//    }

}