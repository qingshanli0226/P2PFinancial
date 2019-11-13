package com.bwei.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class NetcomentManager {
//  application初始化
//    baseActivity中交互
//    1单例,获取网络状态,注册广播监听网络,
//    2网络链接状态
//    维护connetstases boolean值
//    connetType(String) 什么网络状态
//    3两个接口,get接口,初始化接口,当前网络回调接口
    private static NetcomentManager instance;
    private static Context context;
    private static boolean connectSatus=false;

    public NetcomentManager(Context context) {
    this.context=context;
    }

    public static NetcomentManager getInstance(Context context){
        if (instance==null){
            instance=new NetcomentManager(context);
        }
        return instance;
    }
    private  ConnectivityManager connectivityManager;
    public void init(){
        connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo.isConnected()){
            connectSatus=true;
        }else{
            connectSatus=false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver,intentFilter);
    }
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()) {
                    connectSatus=true;
                }else{
                    connectSatus=false;
                }
                notifyConnectChanged();
            }

            }
        };


    public interface INetConnectListener {
        void onConnected();
        void onDisConnected();
    }
    //使用链表的目的是，可能同时有多个页面监听当前网络的变化s
    private List<INetConnectListener> iNetConnectListenerList = new LinkedList<>();
    private void notifyConnectChanged(){
        for (INetConnectListener listener :iNetConnectListenerList){
            if (connectSatus){
                listener.onConnected();
            }else{
                listener.onDisConnected();
            }
        }
    }
    public void registerNetConnectListener(INetConnectListener iNetConnectListener){
        if (!iNetConnectListenerList.contains(iNetConnectListener)&&iNetConnectListener!=null){
            iNetConnectListenerList.add(iNetConnectListener);
        }
    }
    public void unRegisterNetConnectListener(INetConnectListener iNetConnectListener){
        if (!iNetConnectListenerList.contains(iNetConnectListener)&&iNetConnectListener!=null){
            iNetConnectListenerList.remove(iNetConnectListener);
        }
    }
    public boolean isConnectStatus(){
        return connectSatus;
    }


}
