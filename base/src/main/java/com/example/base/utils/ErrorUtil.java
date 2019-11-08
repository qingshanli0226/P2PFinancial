package com.example.base.utils;

import com.example.common.ErrorCodes;
import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorUtil {
    public static ErrorCodes handleError(Throwable e) {
        if (e instanceof SocketTimeoutException) { //肯定是网络请求协议错误.
            return ErrorCodes.HTTP_SOCKET_TIME_OUT_ERROR;
        } else if (e instanceof HttpException) {
            return ErrorCodes.HTTP_ERROR;
        } else if (e instanceof JsonSyntaxException) { //json 解析错误.
            return ErrorCodes.JSON_ERROR;
        } else { //业务错误.
            return ErrorCodes.OTHER_ERROR;
        }
    }
}
