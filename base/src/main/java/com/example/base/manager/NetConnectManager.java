package com.example.base.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class NetConnectManager {
    private Context applicationContext;
    private boolean connectStatus = false;
    private static NetConnectManager instance;
    private ConnectivityManager connectivityManager;

    //使用链表的目的是, 可能同时有多个页面监听
    private List<INetConnectListener> iNetConnectListenerList = new LinkedList<>();

    //单例模式
    private NetConnectManager() {

    }

    public static NetConnectManager getInstance() {
        if (instance == null) {
            instance = new NetConnectManager();
        }
        return instance;
    }

    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
        //1. 获取当前网络连接情况
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //网络状态
        if (networkInfo != null && networkInfo.isConnected()) {
            connectStatus = true;
        }else {
            connectStatus = false;
        }

        //注册广播去监听当前网络连接的变化
        IntentFilter intentFilter = new IntentFilter();
        //监听系统广播
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        //监听
        applicationContext.registerReceiver(connectReceiver, intentFilter);

    }



    private BroadcastReceiver connectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                //网络状态
                if (networkInfo != null && networkInfo.isConnected()) {
                    connectStatus = true;
                }else {
                    connectStatus = false;
                }

                //回调通知网络连接的变化
                notifyConnectChanged();
            }
        }
    };

    //回调通知网络连接的变化
    private void notifyConnectChanged() {
        for (INetConnectListener listener : iNetConnectListenerList) {
            if (connectStatus) {
                listener.onConnected();
            } else {
                listener.onDisConnected();
            }
        }
    }

    //注册
    public void registerNetConnectListener(INetConnectListener iNetConnectListener) {
        if (!iNetConnectListenerList.contains(iNetConnectListener) && iNetConnectListener != null) {
            iNetConnectListenerList.add(iNetConnectListener);
        }
    }

    //注销
    public void unRegisterNetConnectListener(INetConnectListener iNetConnectListener) {

        if (iNetConnectListener != null && iNetConnectListenerList.contains(iNetConnectListener)) {
            iNetConnectListenerList.remove(iNetConnectListener);
        }
    }

    //当前的获取网路状态
    public boolean isConnectStatus() {
        return connectStatus;
    }

    public interface INetConnectListener {
        void onConnected();

        void onDisConnected();
    }
}
