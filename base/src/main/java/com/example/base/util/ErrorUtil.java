package com.example.base.util;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorUtil {
    public static String hanleError(Throwable e){
        if (e instanceof SocketTimeoutException){
            //网络请求协议错误
            return ((SocketTimeoutException) e).getMessage();
        }else if (e instanceof HttpException){
            return ((HttpException) e).getMessage();
        }else if (e instanceof JsonSyntaxException){
            //json解析错误
            return ((JsonSyntaxException)e).getMessage();
        }else {
            //业务错误
            return ((RuntimeException) e).getMessage();
        }

    }
}
