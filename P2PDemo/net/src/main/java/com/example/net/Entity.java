package com.example.net;

import java.util.List;

public class Entity<T> {


    List<T> imgs;
    T data;

    public List<T> getImgs() {
        return imgs;
    }

    public void setImgs(List<T> imgs) {
        this.imgs = imgs;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
