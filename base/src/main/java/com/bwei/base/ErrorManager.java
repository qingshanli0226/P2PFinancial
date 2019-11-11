package com.bwei.base;

import com.bwei.common.P2PError;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.nio.file.FileSystemException;

import retrofit2.HttpException;

public class ErrorManager {
    public static P2PError handleError(Throwable e){
        if (e instanceof SocketTimeoutException){
            return P2PError.HTTP_SOCKET_TIME_OUT;
        }else if (e instanceof HttpException){
            return P2PError.HTTP_ERROR;
        }else if (e instanceof JsonSyntaxException){
            return P2PError.JSON_ERROR;
        }else if (e instanceof FileNotFoundException){
            return P2PError.FILE_ERROR;
        }else {
            return P2PError.ELSE_ERROR;
        }
    }
}
