package com.example.p2pmonthhomework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p2pmonthhomework.R;

import java.util.ArrayList;
import java.util.List;

public abstract class MyRecyclerAdapter<T> extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private List<T> list = new ArrayList<>();
    private int LayoutId;

    public MyRecyclerAdapter(int layoutId) {
        LayoutId = layoutId;
    }

    public void refresh(List<T> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(LayoutId, parent, false);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        onBind(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract void onBind(MyRecyclerViewHolder holder,int position);
}
