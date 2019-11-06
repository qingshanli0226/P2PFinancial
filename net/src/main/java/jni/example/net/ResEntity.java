package jni.example.net;

import java.util.List;

public class ResEntity<T,L> {
    private T data;
    private List<L> list_data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<L> getList_data() {
        return list_data;
    }

    public void setList_data(List<L> list_data) {
        this.list_data = list_data;
    }
}
