package com.example.administrator.p2pdemotext.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class InvestAllAdapter extends RecyclerView.Adapter<InvestAllAdapter.Myhoder> {

    @NonNull
    @Override
    public Myhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Myhoder myhoder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Myhoder extends RecyclerView.ViewHolder{

        public Myhoder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
