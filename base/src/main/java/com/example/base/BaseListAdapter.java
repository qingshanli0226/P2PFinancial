package com.example.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseListAdapter<T> extends BaseAdapter {

    Context context;
    List<T> list = new ArrayList<>();

    public BaseListAdapter(Context context) {
        this.context = context;
    }

    public void reFresh(List<T> newList) {
        list.clear();
        list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    class BaseListHolder {

    }
}
