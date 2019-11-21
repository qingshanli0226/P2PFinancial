package com.example.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.NetConnectManager;

public abstract class BaseFragment extends Fragment implements NetConnectManager.INetConnectListener {

    View views;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int view = setView();
        views= inflater.inflate(view, container, false);
        getBaseView();


        inItData();
        NetConnectManager.getInstance().registerNetConnectListener(this);

        return views;
    }

   public View getBaseView(){
        return views;
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetConnectManager.getInstance().unRegisterNetConnectListener(this);
    }

    public boolean isConnected(){
        return NetConnectManager.getInstance().isConnectStatus();
    }


    protected abstract void inItData();

    protected abstract int setView();


}
