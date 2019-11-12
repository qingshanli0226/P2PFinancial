package jni.example.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class NetConnectManager {
    private Context applicationContext;
    //TODO 是否连接
    private boolean isConnect = false;
    //TODO 存放多个页面，可能会用到网络监听
    private List<INetConnectListener> iNetConnectListenerList = new LinkedList<>();
    //TODO 网络连接管理类
    private ConnectivityManager connectivityManager;
    //TODO 构造一个单例
    private static NetConnectManager connectManager;
    private NetConnectManager() {}
    public static NetConnectManager getInstance(){
        if(connectManager==null){
            connectManager = new NetConnectManager();
        }
        return connectManager;
    }

    public void init(Context applicationContext){
        this.applicationContext = applicationContext;
        //TODO 获取网络当前连接状态
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //TODO 检查网络连接
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            isConnect = true;
        }else{
            isConnect = false;
        }

        //TODO 注册广播来监听当前网络连接的变化
        IntentFilter intentFilter = new IntentFilter();
        //TODO 监听系统广播
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        applicationContext.registerReceiver(broadcastReceiver,intentFilter);

    }
    private void notifyConnectChanged() {
        for(INetConnectListener listener : iNetConnectListenerList) {
            if (isConnect) {
                listener.onConnected();
            } else {
                listener.onDisConnected();
            }
        }
    }

    //TODO 注册监听网络变化的广播
    public void registerNetConnectListener(INetConnectListener iNetConnectListener) {
        if (!iNetConnectListenerList.contains(iNetConnectListener) && iNetConnectListener != null) {
            iNetConnectListenerList.add(iNetConnectListener);
        }
    }
    //TODO 注销监听网络变化的广播
    public void unRegisterNetConnectListener(INetConnectListener iNetConnectListener){
        if (iNetConnectListener!= null && iNetConnectListenerList.contains(iNetConnectListener)) {
            iNetConnectListenerList.remove(iNetConnectListener);
        }
    }

    //TODO 让其他类获取当前网络状态
    public boolean getConnectStatus() {
        return isConnect;
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager
                        .getActiveNetworkInfo();
                if (networkInfo!=null && networkInfo.isConnected()) {
                    isConnect = true;
                } else {
                    isConnect = false;
                }
                //TODO 回调通知网络连接的变化
                notifyConnectChanged();
            }
        }
    };

    public interface INetConnectListener{
        //TODO 连接后
        void onConnected();
        //TODO 连接中断
        void onDisConnected();
    }
}
