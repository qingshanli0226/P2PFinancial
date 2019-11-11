package com.example.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.base.view.IBaseView;
import com.example.commen.util.LoadingPage;

public abstract class BaseFragment<T> extends Fragment implements IBaseView<T> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("111111222", "showLoading: 这是onCreateView");
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        initData();
        initTopTitle();

    }

    //初始化数据
    protected void initData(){

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


    @Override
    public void showLoading(int showCode) {
        LoadingPage.getInstance().setActivityAttach(getActivity()).show(showCode);
//        LoadingPage.getInstance().setViewAttach().show(showCode);
    }

    @Override
    public void hideLoading(int showCode) {
        LoadingPage.getInstance().setActivityAttach(getActivity()).hideLoading();
    }
}
