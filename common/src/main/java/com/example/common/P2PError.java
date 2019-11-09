package com.example.common;

//枚举错误类
public enum P2PError {

    //根据code,返回message值
    HTTP_ERROR(1001, "网络错误"),
    HTTP_SOCKET_TIME_OUT_ERROR(1002, "网络连接超时错误"),
    JSON_ERROR(2001, "数据格式不对"),
    OTHER_ERROR(10000, "其他错误");

    private int errorCode;
    private String errorMessage;

    P2PError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
