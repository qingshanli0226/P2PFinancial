package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.common.R;
import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.common.diyviews.presenter.PresenterBaseView;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T> extends Fragment {
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
        return fragmentView;
    }

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
