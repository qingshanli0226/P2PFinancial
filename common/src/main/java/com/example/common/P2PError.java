package com.example.common;

public enum  P2PError {
    HTTP_ERROT("网络错误",1001),
    HTTP_SOCKET_TIME_ERROR("网络连接超时错误",1002),
    JSON_ERROR("数据格式不对",2001),
    FILE_OPEN_ERROR("打开文件错误",3001),
    BUSINESS_ERROR("获取数据失败",4001),
    MEM_ERROR("内存处理错误",5001),
    OTHTR_ERROR("其他错误",10000)
    ;
    private String errorMessage;
    private int errorCode;

    P2PError(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
