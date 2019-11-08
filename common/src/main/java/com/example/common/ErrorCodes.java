package com.example.common;

public enum  ErrorCodes {

    HTTP_ERROR(1001, "网络错误"),

    HTTP_SOCKET_TIME_OUT_ERROR(1002, "网络连接超时错误"),

    JSON_ERROR(2001, "数据格式不对"),

    FILE_OPEN_ERROR(3001, "打开文件错误"),

    BUSINESS_ERROR(4001, "获取数据失败"),

    MEM_ERROR(5001, "内存处理错误"),

    OTHER_ERROR(10000, "其他错误");

    private String errorMessage;
    private int errorCode;

    private ErrorCodes(int errorCode, String errorMessage) {
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
