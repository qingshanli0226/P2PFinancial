package com.example.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getcontentview(inflater, container);
        initData();
        setListener();
        return view;
    }
    protected abstract View getcontentview(LayoutInflater inflater, ViewGroup container);
    //初使化数据
    public abstract void initData();

    //设置监听
    public abstract  void  setListener();

}
