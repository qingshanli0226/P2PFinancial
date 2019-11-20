package com.example.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.base.view.IBaseView;
import com.example.commen.ActivityInstanceManager;
import com.example.commen.NetConnectManager;
import com.example.commen.P2PError;

import java.util.List;

public abstract class BaseActivity<T> extends AppCompatActivity implements NetConnectManager.INetConnectListener, IBaseView<T> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();

        initData(); //一般在子线程中操作

        ActivityInstanceManager.addActivity(this);

        NetConnectManager.getInstance().registerNetConnectListener(this);
    }

    public boolean isConnected(){
        return NetConnectManager.getInstance().isConnectStatus();
    }

    //让子类提供布局
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();


    /**
     * 网路状态
     */
    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityInstanceManager.removeActivity(this);
        NetConnectManager.getInstance().unRegisterNetConnectListener(this);
    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, T data) {

    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<T> data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {

    }

    @Override
    public void showLoading(int showCode) {

    }

    @Override
    public void hideLoading(int showCode) {

    }
}
