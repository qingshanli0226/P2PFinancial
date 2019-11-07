package com.example.net;

public class ResEntity<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResEntity{" +
                "data=" + data +
                '}';
    }
}
