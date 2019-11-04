package com.example.base.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        initTopTitle();
    }

    //提供布局
    @LayoutRes
    protected abstract int getLayoutId();

    //初始化View
    protected abstract void initView(View view, Bundle savedInstanceState);

    //初始化标题栏
    protected abstract void initTopTitle();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
