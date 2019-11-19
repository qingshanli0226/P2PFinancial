package com.bw.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public List<T> list;

    public MyBaseAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseHolder<T> holder;
        if (view == null){
            holder = getHolder();
        }else {
            holder = (BaseHolder<T>) view.getTag();
        }

        holder.setData(list.get(i));

        return null;
    }

    protected abstract BaseHolder<T> getHolder();
}
