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
import com.example.base.manager.NetConnectManager;
import com.example.commen.P2PError;

import java.util.List;

public abstract class BaseFragment<T> extends Fragment implements IBaseView<T>, NetConnectManager.INetConnectListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //网络状态判断
        NetConnectManager.getInstance().registerNetConnectListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("111111222", "showLoading: 这是onCreateView");
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        initTopTitle();
        initData();
    }

    //提供布局
    @LayoutRes
    protected abstract int getLayoutId();

    //初始化View
    protected abstract void initView(View view, Bundle savedInstanceState);

    //初始化标题栏
    protected abstract void initTopTitle();

    //初始化数据
    protected void initData() {}



    public boolean isConnected() {
        return NetConnectManager.getInstance().isConnectStatus();
    }

    /**
     * 网络连接判断
     */
    @Override
    public void onConnected() {}

    @Override
    public void onDisConnected() {}



    /**
     * 加载Loading
     * @param showCode
     */
    @Override
    public void showLoading(int showCode) {
//        LoadingPage.getInstance().setActivityAttach(getActivity()).show(showCode);
    }

    @Override
    public void hideLoading(int showCode) {
//        LoadingPage.getInstance().setActivityAttach(getActivity()).hideLoading();
    }



    @Override
    public void onHttpRequestDataSuccess(int requestCode, T data) {}

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<T> data) {}

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {}


    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetConnectManager.getInstance().unRegisterNetConnectListener(this);
    }
}
