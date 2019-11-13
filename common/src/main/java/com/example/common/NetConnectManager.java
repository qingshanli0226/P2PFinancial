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
    private Context context;
    private boolean connectStatus=false;
    private static NetConnectManager netConnectManager;
    private ConnectivityManager connectivityManager;
    private List<INetListener> iNetListeners=new LinkedList<>();
    public NetConnectManager() {
    }
    public static NetConnectManager getInstance(){
        if (netConnectManager ==null){
            netConnectManager =new NetConnectManager();
        }
        return netConnectManager;
    }
    public void init(Context ctv){
        this.context=ctv;
        connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        if (network!=null && network.isConnected()){
            connectStatus=true;
        }else {
            connectStatus=false;
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,filter);
    }
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
               if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                   connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                   NetworkInfo network = connectivityManager.getActiveNetworkInfo();
                   if (network!=null && network.isConnected()){
                       connectStatus=true;
                   }else {
                       connectStatus=false;
                   }
                   notifyConnectChanged();
               }
        }


    };
    private void notifyConnectChanged() {
        for (INetListener listener:iNetListeners) {
              if (true){
                  listener.onConnected();
              }else{
                  listener.onDisConnected();
              }
        }

    }
    public boolean isConnectStatus(){
        return connectStatus;
    }
    public interface  INetListener{
           void onConnected();
           void onDisConnected();
    }


    public void registINetListener(INetListener iNetListener){
        if (iNetListener!=null && !iNetListeners.contains(iNetListener)){
            iNetListeners.add(iNetListener);
        }
    }

    public void unregistINetListener(INetListener iNetListener){
        if (iNetListener!=null && iNetListeners.contains(iNetListener)){
            iNetListeners.remove(iNetListener);
        }
    }
}
