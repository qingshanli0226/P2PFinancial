package com.example.network;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class AppErrorUtil {
    public static AppErrorType findError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            return AppErrorType.NET_RRROR;
        } else if (e instanceof IOException) {
            return AppErrorType.FILE_RRROR;
        } else if (e instanceof JSONException) {
            return AppErrorType.DATA_RRROR;
        } else {
            return AppErrorType.MY_RRROR;
        }
    }
}
