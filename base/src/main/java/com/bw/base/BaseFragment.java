package com.bw.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bw.common.NetConnectManager;

public abstract class BaseFragment extends Fragment implements NetConnectManager.INetConnectListener {

    View views;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setView();
        views = inflater.inflate(setView(),container,false);

        getBaseView();

        initData();
        NetConnectManager.getInstance().registerNetConnectListener(this);

        initView();

        return views;
    }

    protected abstract void initView();

    protected View getBaseView(){
        return views;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetConnectManager.getInstance().unRegisterNetConnectListener(this);
    }

    public boolean isConnected(){
        return NetConnectManager.getInstance().isConnectStatus();
    }

    protected abstract void initData();

    protected abstract int setView();

}
