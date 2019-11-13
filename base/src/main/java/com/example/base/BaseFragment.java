package com.example.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.NetConnectManager;

public abstract class BaseFragment extends Fragment implements IFragment, NetConnectManager.INetListener {
    protected View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutId(),container,false);
        initView();
        initData();
        setListener();
        NetConnectManager.getInstance().registINetListener(this);
        return view;
    }
    //初使化数据
    protected abstract void initView();
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetConnectManager.getInstance().unregistINetListener(this);
    }
    public boolean isConnected(){
        return  NetConnectManager.getInstance().isConnectStatus();
    }


    //设置监听
    public abstract  void  setListener();

}
