package com.example.common;

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
    private boolean connertstatus=false;
    private static NetConnectManager instance;
    ConnectivityManager connectivityManager;
    //使用链表的目的是, 可能同时有多个页面监听
    private List<INetConnectListener> iNetConnectListenerList = new LinkedList<>();

    public NetConnectManager() {

    }

    public static NetConnectManager getInstance(Context applicationContext){
        if (instance==null){
            instance=new NetConnectManager();
        }
        return instance;
    }
    public void init(){
        connectivityManager= (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager
                .getActiveNetworkInfo();
        //网络的状态
        if (networkInfo.isConnected()){
            connertstatus=true;
        }else {
            connertstatus=false;
        }
        //注册广播去舰艇当前网络连接的变化
        IntentFilter intentFilter=new IntentFilter();
        //监听系统的广播
        intentFilter.addAction(connectivityManager.CONNECTIVITY_ACTION);
        //监听
        applicationContext.registerReceiver(connectReceiver,intentFilter);
    }
    private BroadcastReceiver connectReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.EXTRA_NO_CONNECTIVITY)){
                NetworkInfo networkInfo=connectivityManager
                        .getActiveNetworkInfo();
                //网络状态
                if (networkInfo.isConnected()){
                    connertstatus=true;
                }else {
                    connertstatus=false;
                }
                notifyConnectChanger();
            }
        }
    };

    //回调通知网络连接的变化
    private void notifyConnectChanger(){
        for (INetConnectListener listener:iNetConnectListenerList){
            if (connertstatus){
                listener.onConnected();
            }else {
                listener.onDisConnected();
            }
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
        return connertstatus;
    }


    public interface INetConnectListener{
        void onConnected();
        void onDisConnected();
    }
}

