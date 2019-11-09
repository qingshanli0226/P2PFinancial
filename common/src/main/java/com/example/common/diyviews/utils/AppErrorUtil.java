package com.example.common.diyviews.utils;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class AppErrorUtil {
    public static AppErrorType findError(Throwable e) {
        Log.e("xxx","错误信息:"+e.getMessage());
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
