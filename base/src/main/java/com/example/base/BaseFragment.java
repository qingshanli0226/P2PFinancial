package com.example.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutRes(), container, false);
        initView(view);
        initData();
        return view;
    }

    @LayoutRes
    protected abstract int setLayoutRes();

    protected abstract void initView(View view);

    public void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
