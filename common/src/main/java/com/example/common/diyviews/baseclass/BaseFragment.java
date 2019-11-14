package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected View fragmentView;
    protected Context fragmentContext;

    private Unbinder bind;
    public BaseFragment(Context fragmentContext) {
        this.fragmentContext = fragmentContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建视图  获取上下文  绑定控件
        fragmentView= inflater.inflate(getLayoutId(), container, false);
        fragmentContext=getContext();
        bind = ButterKnife.bind(this, fragmentView);
        initView();
        initData();
        return fragmentView;
    }

    protected abstract void initData();
    protected abstract int getLayoutId();
    protected abstract void initView();

    @Override
    public void onDestroyView() {
        if (bind!=null){
            bind.unbind();
        }
        super.onDestroyView();
    }

}
