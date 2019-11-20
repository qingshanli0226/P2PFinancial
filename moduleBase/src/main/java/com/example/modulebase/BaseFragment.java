package com.example.modulebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import butterknife.ButterKnife;
//Fragment基类
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(),container,false);
        ButterKnife.bind(this,view);
        initData();

        initTitle();
       return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    protected abstract void initTitle();

    protected abstract void initData();

    protected abstract void loadData();

    //绑定布局
    protected abstract int setLayout();
    

}
