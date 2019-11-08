package com.example.p2invest.fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.base.BaseFragment;
import com.example.p2invest.R;

public class LoadFragment extends FrameLayout {
    private static final  int STATELOADING=1;
    private int current=STATELOADING;
    private static final int STATE_SUCCESS = 4;
    private View loading;
    private LayoutParams params;
    private View success;
    private Context context;
    public LoadFragment(@NonNull Context context) {
        super(context,null);
    }

    public LoadFragment(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public LoadFragment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        this.context=context;
        init();
    }

    private void init() {
        params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        if (loading==null){
            loading= LayoutInflater.from(context).inflate(R.layout.loadingpic,null);
            addView(loading,params);
        }
        showSafePage();
    }

    private void showSafePage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
              loading.setVisibility(current == STATELOADING ? View.VISIBLE : View.INVISIBLE);
            }
        }).start();
    }

}
