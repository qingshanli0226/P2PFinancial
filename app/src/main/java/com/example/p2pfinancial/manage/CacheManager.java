package com.example.p2pfinancial.manage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.common.NetConnectManager;
import com.example.p2pfinancial.bean.AllInvestBean2;
import com.example.common.ACache;
import com.example.p2pfinancial.bean.MainBean;
import com.example.p2pfinancial.serviece.CacheService;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;


public class CacheManager {

    private static CacheManager cacheManager;
    private Context context;
    private List<IDataRecivedListener> iDataRecivedListeners = new LinkedList<>();

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
                public void onAllReceived(@NotNull AllInvestBean2 allInvestBean) {
                    for (IDataRecivedListener dataRecivedListener : iDataRecivedListeners) {
                        dataRecivedListener.onAllRecived(allInvestBean);
                    }
                    savaAllBeanLocal(allInvestBean);
                }

                @Override
                public void onDataReceived(@NotNull MainBean bean) {

                    for (IDataRecivedListener dataRecivedListener : iDataRecivedListeners) {
                        dataRecivedListener.onDataRecived(bean);
                    }
                    savaLocal(bean);
                }
            });

            if (!NetConnectManager.getInstance().getConnectStatus()) {
                return;
            } else {
                cacheService.getData();
                cacheService.getAllData();
            }
            NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                @Override
                public void onConnect() {
                    cacheService.getData();
                    cacheService.getAllData();
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

    private void savaAllBeanLocal(AllInvestBean2 allInvestBean) {
        ACache aCache = ACache.get(context);
        aCache.put("AllBean", allInvestBean);
    }

    private void savaLocal(MainBean bean) {
        ACache aCache = ACache.get(context);
        aCache.put("JsonBean", bean);
    }

    public AllInvestBean2 getAllBeanData() {
        ACache aCache = ACache.get(context);
        if (aCache != null) {
            return (AllInvestBean2) aCache.getAsObject("AllBean");
        }
        return null;
    }

    public MainBean getBeanData() {
        ACache aCache = ACache.get(context);
        if (aCache != null) {
            return (MainBean) aCache.getAsObject("JsonBean");
        }
        return null;
    }

    public void registerListener(IDataRecivedListener iDataRecivedListener) {
        if (!iDataRecivedListeners.contains(iDataRecivedListener)) {
            iDataRecivedListeners.add(iDataRecivedListener);
        }
    }

    public void unregisterListener(IDataRecivedListener iDataRecivedListener) {
        iDataRecivedListeners.remove(iDataRecivedListener);
    }

    public interface IDataRecivedListener {
        void onDataRecived(MainBean mainBean);

        void onAllRecived(AllInvestBean2 allInvestBean);
    }

    public void init(Context context) {
        this.context = context;
        Intent intent = new Intent(context, CacheService.class);
        context.startService(intent);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

}
