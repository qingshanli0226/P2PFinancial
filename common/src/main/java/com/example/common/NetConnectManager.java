package com.example.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;


public class NetConnectManager {

    private Context ctx;
    private boolean connectStatus=false;
    private static NetConnectManager NetManager;
    private ConnectivityManager connectivityManager;


    private List<INetConnectListener> iNetConnectListeners =new LinkedList<>();
    public NetConnectManager() {
    }

    public static NetConnectManager getInstance(){
        if(NetManager==null){
            NetManager=new NetConnectManager();
        }
        return NetManager;
    }
    public void init(Context context){
        this.ctx=context;
        connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        //如果当前网络存在和已连接
        if(networkInfo!=null&&networkInfo.isConnected()){
            connectStatus=true;

        }else{
            connectStatus=false;

        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(connectReceiver,intentFilter);


    }
    private void notifyConnectChanged(){
        for (INetConnectListener listener:iNetConnectListeners){
            if(connectStatus){
                listener.onConnected();
            }else{
                listener.onDisConnected();
            }
        }
    }


    private BroadcastReceiver connectReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                connectivityManager =(ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null&&networkInfo.isConnected()){
                    connectStatus=true;
                }else{
                    connectStatus=false;
                }
                notifyConnectChanged();
            }

        }
    };


    //注册网络变化的广播
    public void registerNetConnectListener(INetConnectListener iNetConnectListener){
        if(!iNetConnectListeners.contains(iNetConnectListener) && iNetConnectListener!=null){
            iNetConnectListeners.add(iNetConnectListener);
        }
    }
    //注销网络监听的广播
    public void unRegisterNetConnectListener(INetConnectListener iNetConnectListener){
        if(iNetConnectListeners!=null&& iNetConnectListeners.contains(iNetConnectListener)){
            iNetConnectListeners.remove(iNetConnectListener);
        }
    }
    public boolean isConnectStatus(){
        return connectStatus;
    }
    public interface INetConnectListener{
        void onConnected();
        void onDisConnected();
    }

}
