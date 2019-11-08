package com.example.base.util;

import com.example.common.P2PError;
import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorUtil {
    public static P2PError handleError(Throwable e){
             if (e instanceof SocketTimeoutException){
                 return P2PError.HTTP_SOCKET_TIME_ERROR;
             }else if (e instanceof HttpException){
                 return  P2PError.HTTP_ERROT;
             }else if (e instanceof JsonSyntaxException){
                 return P2PError.JSON_ERROR;
             }
             else {
                 return P2PError.OTHTR_ERROR;
             }

    }
}
