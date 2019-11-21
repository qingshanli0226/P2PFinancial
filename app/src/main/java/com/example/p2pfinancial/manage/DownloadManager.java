package com.example.p2pfinancial.manage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.common.NetConnectManager;
import com.example.p2pfinancial.serviece.CacheService;

public class DownloadManager {

    private static DownloadManager downloadManager;
    private Context context;
    //饿汉式
    public static DownloadManager getInstance() {

        if (downloadManager == null) {
            downloadManager = new DownloadManager();
        }
        return downloadManager;
    }
    
    private CacheService cacheService;//服务
    private INewApkListener iNewApkListener;//监听

    //服务逻辑,异步操作
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取服务对象
            cacheService = ((CacheService.CacheBind) service).getService();
            cacheService.registerDownloadListener(new CacheService.IDownloadListener() {
                @Override
                public void downloadApk() {
                    //更新完Apk调用
                    if (iNewApkListener!=null){
                        //回调给activity]
                        iNewApkListener.onUpApkVersions();
                    }
                }
            });
            //拿到网络状态
            boolean connectStatus = NetConnectManager.getInstance().getConnectStatus();
            if (connectStatus) {//如果有网络
                cacheService.getNewApk();//调用服务更新apk方法
            } else {
                return;
            }

            NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                @Override
                public void onConnect() {
                    //网络是连接状态更新版本
                    cacheService.getNewApk();
                }

                @Override
                public void onDisConnect() {

                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    
    public void registerNewApkListener(INewApkListener iNewApkListener) {
        this.iNewApkListener = iNewApkListener;
    }

    public void unregisterNewApkListener(INewApkListener iNewApkListener) {
        this.iNewApkListener = null;
    }

    public interface INewApkListener {
        void onUpApkVersions();
    }

    //初始化,开启并绑定服务
    public void init(Context context) {
        this.context = context;
        Intent intent = new Intent(context, CacheService.class);
        context.startService(intent);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


}
