package com.example.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.view.MyLoadingPage;

public abstract class BaseFragment extends Fragment implements IBaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        initView(view);

        initData();
        return view;
    }

    @Override
    public abstract int getLayoutId();

    @Override
    public abstract void initView(View view);

    @Override
    public abstract void initData();

    public void showLoading(MyLoadingPage mLoadingPage) {
        mLoadingPage.startLoadingAnimation();
    }

    public void hideLoading(MyLoadingPage mLoadingPage) {
        mLoadingPage.interruptLoadingAnimation();
        mLoadingPage.setLoadingPagedismiss();
    }
}
