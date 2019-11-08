package com.example.network;

import android.util.Log;

public enum AppErrorType {
    //网络 1000   存储or文件2000   数据3000  业务4000

    NET_RRROR(1000,"网络错误"),
    FILE_RRROR(2000,"文件错误"),
    DATA_RRROR(3000,"数据错误"),
    MY_RRROR(4000,"业务错误");

    private int errorCode;
    private String errorStr;

    AppErrorType(int errorCode, String errorStr) {
        this.errorCode = errorCode;
        this.errorStr = errorStr;
        Log.e("xxxx","错误码为:"+errorCode+"属于"+errorStr);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorStr() {
        return errorStr;
    }
}
