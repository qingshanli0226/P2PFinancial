package com.example.base.util;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorUtil {
    public static String handleError(Throwable e){
             if (e instanceof SocketTimeoutException){
                 return  ((SocketTimeoutException)e).getMessage();
             }else if (e instanceof HttpException){
                 return  ((HttpException)e).getMessage();
             }else if (e instanceof JsonSyntaxException){
                 return  ((JsonSyntaxException)e).getMessage();
             }
             else {
                 return "";
             }

    }
}
