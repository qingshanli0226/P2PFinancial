package com.example.base.utils;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErroUtils {

    public static String handlerError(Throwable e){
        if(e instanceof SocketTimeoutException){
            return ((SocketTimeoutException)e).getMessage();
        }else if(e instanceof HttpException){
            return ((HttpException)e).getMessage();
        }else if(e instanceof JsonSyntaxException){
            return ((JsonSyntaxException)e).getMessage();
        }else if(e instanceof NullPointerException){
            return ((NullPointerException)e).getMessage();
        }
        else {
            return ((RuntimeException)e).getMessage();
        }
    }

}
