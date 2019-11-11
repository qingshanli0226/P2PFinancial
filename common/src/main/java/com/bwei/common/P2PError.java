package com.bwei.common;

public enum  P2PError {
    HTTP_ERROR(1001,"网络错误"),
    HTTP_SOCKET_TIME_OUT(1002,"网络链接超时错误"),
    JSON_ERROR(2001,"数据格式不对"),
    FILE_ERROR(3001,"打开文件夹错误"),
    BUTINESS_ERROR(4001,"获取数据失败错误"),
    MEM_ERROR(5001, "内存处理错误"),
    ELSE_ERROR(10000,"qta错误");
    private int errorCode;
    private String errorMessenger;
    P2PError(int errorCode, String errorMessenger){
        this.errorCode=errorCode;
        this.errorMessenger=errorMessenger;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessenger() {
        return errorMessenger;
    }
}
