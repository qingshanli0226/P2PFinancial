package com.example.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.util.LinkedList;

public class NetConnectManager {

    private static NetConnectManager netConnectManager;//单例对象
    private Context applicationContext;//上下文
    private ConnectivityManager connectivityManager;//管理网络连接的管理类
    private boolean connectStatus = false;//标记连接状态
    //链表集合增删快
    private LinkedList<INetConnectListener> iNetConnectListeners = new LinkedList<>();

    //饿汉式单例
    public static NetConnectManager getInstance() {
        if (netConnectManager == null) {
            netConnectManager = new NetConnectManager();
        }
        return netConnectManager;
    }

    //初始化
    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
        //获得处理网络连接的服务
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            //返回网络信息
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //如果不为空并且网络连接
            if (networkInfo != null && networkInfo.isConnected()) {
                connectStatus = true;
            } else {//网络未连接
                connectStatus = false;
            }
        }
        //创建通知
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        applicationContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager != null) {
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        connectStatus = true;
                    } else {
                        connectStatus = false;
                    }
                    notifyConnectChanged();
                }
            }
        }
    };

    //注册网络连接状态监听
    public void registerNetConnectListener(INetConnectListener iNetConnectListener) {
        if (!iNetConnectListeners.contains(iNetConnectListener) && iNetConnectListener != null) {
            iNetConnectListeners.add(iNetConnectListener);
        }
    }

    //注销
    public void unregisterNetConnectListener(INetConnectListener iNetConnectListener) {
        if (iNetConnectListener != null && iNetConnectListeners.contains(iNetConnectListener)) {
            iNetConnectListeners.remove(iNetConnectListener);
        }
    }

    private void notifyConnectChanged() {
        for (INetConnectListener connectListener : iNetConnectListeners) {
            //网络是连接状态的监听
            if (connectStatus) {
                connectListener.onConnect();
            } else {//未连接状态的监听
                connectListener.onDisConnect();
            }
        }
    }

    public interface INetConnectListener {
        void onConnect();

        void onDisConnect();
    }

    public boolean getConnectStatus() {
        return connectStatus;
    }
}
