package com.example.p2pfinancial;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.common.NetConnectManager;
import com.example.p2pfinancial.serviece.CacheService;

import java.util.LinkedList;
import java.util.List;

public class DownloadManager {

    private static DownloadManager downloadManager;
    private Context context;

    public static DownloadManager getInstance() {

        if (downloadManager == null) {
            downloadManager = new DownloadManager();
        }
        return downloadManager;
    }
    
    private CacheService cacheService;
    private INewApkListener iNewApkListener;
    
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            cacheService = ((CacheService.CacheBind) service).getService();
            cacheService.registerDownloadListener(new CacheService.IDownloadListener() {
                @Override
                public void downloadApk() {
                    if (iNewApkListener!=null){
                        iNewApkListener.upApkVersions();
                    }
                }
            });

            boolean connectStatus = NetConnectManager.getInstance().getConnectStatus();
            if (connectStatus) {
                cacheService.getNewApk();
            } else {
                return;
            }

            NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                @Override
                public void onConnect() {
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
        void upApkVersions();
    }


    public void init(Context context) {
        this.context = context;
        Intent intent = new Intent(context, CacheService.class);
        context.startService(intent);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


}
