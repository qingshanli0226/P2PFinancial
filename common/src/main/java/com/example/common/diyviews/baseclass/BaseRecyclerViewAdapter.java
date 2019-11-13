package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewViewHolder> {
    private Context context;
    private ArrayList<T> list=new ArrayList<>();
    private int layoutId;

    public BaseRecyclerViewAdapter(Context context, ArrayList<T> list, int layoutId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public BaseRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseRecyclerViewViewHolder(LayoutInflater.from(context).inflate(layoutId,null));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewViewHolder holder, int position) {
        setViewData(holder,position);
    }

    protected abstract void setViewData(BaseRecyclerViewViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }
}
