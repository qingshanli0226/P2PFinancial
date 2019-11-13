package com.example.p2pmonthhomework.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.view.MyRoundView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> array = new SparseArray<>();

    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private View getView(int id){
        View view = array.get(id);

        if(view==null){
            view = itemView.findViewById(id);
            array.put(id,view);
        }
        return view;
    }

    public void setText(int id,String text){
        TextView textView = (TextView) getView(id);
        if(textView!=null){
            textView.setText(text);
        }
    }

    public void setMyRoundProgress(int id,int progress){
        MyRoundView myRoundView = (MyRoundView) getView(id);
        if(myRoundView!=null){
            myRoundView.setProgress(progress,1);
        }
    }
}
