package com.example.p2pfinancial;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.IBinder;

import com.example.common.NetConnectManager;
import com.example.p2pfinancial.bean.MainBean;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {

    private static CacheManager cacheManager;
    private Context context;
    private List<IDataRecivedListener> iDataRecivedListeners = new LinkedList<>();
    private IDataRecivedListener iDataRecivedListener;

    public static CacheManager getInstance() {
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    private CacheService cacheService;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            cacheService = ((CacheService.CacheBind) service).getService();
            cacheService.registerListener(new CacheService.IDataInterface() {
                @Override
                public void onDataReceived(@NotNull MainBean bean) {
                    iDataRecivedListener.onDataRecived(bean);

                    savaLocal(bean);
                }
            });

            if (!NetConnectManager.getInstance().getConnectStatus()) {
                return;
            } else {
                cacheService.getData();
            }
            NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                @Override
                public void onConnect() {
                    cacheService.getData();
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

    private void savaLocal(MainBean bean) {
        SharedPreferences jsonBean = context.getSharedPreferences("JsonBean", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = jsonBean.edit();
        
    }

    public void registerListener(IDataRecivedListener iDataRecivedListener) {
        if (!iDataRecivedListeners.contains(iDataRecivedListener)) {
            iDataRecivedListeners.add(iDataRecivedListener);
        }
    }

    public void unregisterListener(IDataRecivedListener iDataRecivedListener) {
        if (iDataRecivedListeners.contains(iDataRecivedListener)) {
            iDataRecivedListeners.remove(iDataRecivedListener);
        }
    }

    interface IDataRecivedListener {
        void onDataRecived(MainBean mainBean);
    }

    private void init(Context context) {
        this.context = context;
        Intent intent = new Intent(context, CacheService.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

}
