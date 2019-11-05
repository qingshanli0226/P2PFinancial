package com.example.commen.util;

import org.json.JSONException;

import java.net.HttpRetryException;
import java.net.SocketTimeoutException;

public class ErrorUtil {
    public static String handleError(Throwable e){
        if (e instanceof SocketTimeoutException){ //肯定是网络请求协议错误

            return ((SocketTimeoutException)e).getMessage();
        }else if (e instanceof HttpRetryException){  //Http重试异常

            return ((HttpRetryException)e).getMessage();
        }else if (e instanceof JSONException){ //JSON异常

            return ((JSONException)e).getMessage();
        }else {

            return e.getMessage();
        }
    }
}
