package com.example.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

public class NetConnectManager {
    private Context applicationContext;
    private boolean connertstatus=false;
    private static NetConnectManager instance;
    ConnectivityManager connectivityManager;

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
        if (networkInfo.isConnected()){
            connertstatus=true;
        }else {
            connertstatus=false;
        }
        //注册广播去舰艇当前网络连接的变化
        IntentFilter intentFilter=new IntentFilter();
        //监听系统的广播
        intentFilter.addAction(connectivityManager.CONNECTIVITY_ACTION);
        applicationContext.registerReceiver(connectReceiver,intentFilter);
    }
    private BroadcastReceiver connectReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.EXTRA_NO_CONNECTIVITY)){
                NetworkInfo networkInfo=connectivityManager
                        .getActiveNetworkInfo();
                if (networkInfo.isConnected()){
                    connertstatus=true;
                }else {
                    connertstatus=false;
                }
            }
        }
    };
}

