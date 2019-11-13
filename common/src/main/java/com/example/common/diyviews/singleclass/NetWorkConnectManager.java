package com.example.common.diyviews.singleclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class NetWorkConnectManager {
    private Context context;
    private boolean flag=false;
    private static ConnectivityManager connectivityManager;
    private static NetWorkConnectManager netWorkConnectManager;
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.CONNECTIVITY_ACTION);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
                    flag=true;
                }else {
                    flag=false;
                }

            }
        }
    };
    private NetWorkConnectManager() {
    }
    public static NetWorkConnectManager getInstance(){
        if (netWorkConnectManager==null){
            netWorkConnectManager=new NetWorkConnectManager();

        }
        return netWorkConnectManager;
    }

    public void init(Context context){
        this.context=context;
        connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Android 10 版本禁用
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // 是否连接移动网     是否连接wifi
        if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){

        }
        if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI){

        }
        if (networkInfo!=null&&networkInfo.isConnected()){
            flag=true;
        }else {
            flag=false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,intentFilter);
    }
}
