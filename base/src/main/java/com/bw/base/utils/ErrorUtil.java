package com.bw.base.utils;

import com.google.gson.JsonSyntaxException;
import retrofit2.HttpException;

import java.net.SocketTimeoutException;

public class ErrorUtil {

    public static String handleError(Throwable e){

        if (e instanceof SocketTimeoutException){
            return ((SocketTimeoutException)e).getMessage();
        }else if (e instanceof HttpException){
            return ((HttpException)e).getMessage();
        }else if (e instanceof JsonSyntaxException){
            return ((JsonSyntaxException)e).getMessage();
        }else if (e instanceof NullPointerException){
            return ((NullPointerException)e).getMessage();
        }else if (e instanceof IllegalAccessException){
            return ((IllegalAccessException)e).getMessage();
        }else if (e instanceof ClassCastException){
            return ((ClassCastException)e).getMessage();
        }else {
            return ((RuntimeException)e).getMessage();
        }


        //肯定是网络请求协议错误
//        if (e instanceof SocketTimeoutException){
//            return P2PError.HTTP_SOCKET_TIME_OUT_ERROR;
//        }else if (e instanceof HttpException){
//            return P2PError.HTTP_ERROR;
//        }else if (e instanceof JsonSyntaxException){//json解析错误
//            return P2PError.JSON_ERROR;
//        }else {//业务错误
//            return P2PError.OTHER_ERROR;
//        }
    }

}
