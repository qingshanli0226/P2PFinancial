package com.example.common.diyviews.baseclass;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> array=new SparseArray<>();
    public BaseRecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public View getView(int id){
        View view=array.get(id);
        if (view==null){
            view=itemView.findViewById(id);
            array.put(id,view);
        }
        return view;
    }
}
