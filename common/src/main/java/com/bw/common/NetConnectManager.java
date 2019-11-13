package com.bw.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

//Manager类为单例
public class NetConnectManager {

    private Context applicationContext;
    //网络连接状态
    private boolean connectStatus = false;
    private static NetConnectManager instance;
    private ConnectivityManager connectivityManager;
    //使用链表的目的是，可能同时有多个页面监听当前网络的变化
    private List<INetConnectListener> iNetConnectListeners = new LinkedList<>();

    public NetConnectManager() {
    }

    public static NetConnectManager getInstance() {

        if (instance == null){
            instance = new NetConnectManager();
        }

        return instance;
    }

    public void init(Context applicationContext){
        //获取当前网络连接情况
        this.applicationContext = applicationContext;
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            connectStatus = true;
        }else {
            connectStatus = false;
        }

        //注册广播去监听当前网络连接的变化
        IntentFilter intentFilter = new IntentFilter();
        //监听系统广播
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        //监听
        applicationContext.registerReceiver(connectReceiver,intentFilter);
    }

    private void notifyConnectChanged(){
        for (INetConnectListener listener : iNetConnectListeners){
            if (connectStatus){
                listener.onConnected();
            }else {
                listener.onDisConnected();
            }
        }
    }

    //网络变化的广播
    private BroadcastReceiver connectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()){
                    connectStatus = true;
                }else {
                    connectStatus = false;
                }
                //回调通知网络连接的变化
                notifyConnectChanged();
            }
        }
    };

    //注册监听网络变化的广播
    public void registerNetConnectListener(INetConnectListener iNetConnectListener){
        if (!iNetConnectListeners.contains(iNetConnectListener) && iNetConnectListener != null){
            iNetConnectListeners.add(iNetConnectListener);
        }
    }

    //注销监听网络变化的广播
    public void unRegisterNetConnectListener(INetConnectListener iNetConnectListener){
        if (iNetConnectListener != null && iNetConnectListeners.contains(iNetConnectListener)){
            iNetConnectListeners.remove(iNetConnectListener);
        }
    }

    //让其他类获取当前网络连接的状态
    public boolean isConnectStatus(){
        return connectStatus;
    }

    public interface INetConnectListener {

        void onConnected();
        void onDisConnected();

    }
}