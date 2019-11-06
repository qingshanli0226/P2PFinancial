package com.example.net;

import java.util.List;

public class ResEntity<T> {
    private List<T> listData;
    private T data;

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
