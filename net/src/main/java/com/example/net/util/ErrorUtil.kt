package com.example.net.util

import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.lang.RuntimeException
import java.net.SocketTimeoutException

object ErrorUtil {

    fun handleError(e: Throwable): String {
        return if (e is SocketTimeoutException) { //肯定是网络请求协议错误.
            e.message!!
        } else if (e is HttpException) {
            e.message!!
        } else if (e is JsonSyntaxException) { //json 解析错误.
            e.message!!
        } else { //业务错误.
            e.message!!
        }
    }
}