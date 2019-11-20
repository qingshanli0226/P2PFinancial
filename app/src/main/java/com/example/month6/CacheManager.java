package com.example.month6;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.common.diyviews.utils.ACache;

import java.util.ArrayList;

public class CacheManager {
    public static CacheService cacheService;
    //用于view和manager的通信
    private static ArrayList<ManagerViewListener> managerViewListeners;
    public static void registerListener(ManagerViewListener managerViewListener){
        managerViewListeners.add(managerViewListener);
    }
    public static void unRegisterListener(ManagerViewListener managerViewListener){
        managerViewListeners.remove(managerViewListener);
    }

    private static ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            cacheService=((CacheService.MyBinder) service).getSetvice();
            cacheService.setCacheServiewlistener(new CacheServiewlistener() {
                @Override
                public void onSuccess(Object object) {
                    for (ManagerViewListener i:managerViewListeners){
                        i.onDataOld();
                    }
                }

                @Override
                public void onUpdata(Object object) {

                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public  static void init(Context context){
        ACache cache=ACache.get(context);
        Object data = cache.getAsObject("data");
        Intent intent = new Intent(context, CacheService.class);
        context.startService(intent);
        context.bindService(intent,connection, Service.BIND_AUTO_CREATE);
    }

}
