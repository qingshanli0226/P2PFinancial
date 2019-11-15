package com.example.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.LruCache;

import com.example.common.Bean.HomeBaen;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CacheManager {


    private static CacheManager cacheManager;
    private Context context;
    CacheService cacheService;

    private List<IHomeReceivedListener> ihListener= new LinkedList<>();
    private LruCache<String, Bitmap> bitmapLruCache =new LruCache<>((int)(Runtime.getRuntime().maxMemory()/8));

    public CacheManager() {

    }
    public static CacheManager getInstance(){
        if(cacheManager==null){
            cacheManager=new CacheManager();
        }
        return cacheManager;
    }
    public void init(Context context){
        this.context=context;
        ServiceConnection serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                cacheService = ((CacheService.MyBinder) service).getCacheService();
                //注册监听通知数据已获取到
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void HomeDataReceived(HomeBaen homeBaen) {
                        for (IHomeReceivedListener listener:ihListener){
                            listener.HomeDataReceived(homeBaen);
                        }
                        saveLocal(homeBaen);
                    }
                });
                if(!NetConnectManager.getInstance().isConnectStatus()){
                    return;
                }else{
                    cacheService.getData();
                }

                NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        cacheService.getData();
                    }

                    @Override
                    public void onDisConnected() {

                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(context,CacheService.class);
        context.startService(intent);
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);



    }




    private void saveLocal(HomeBaen homeBaen){
        Log.e("##Save",homeBaen.toString());
        ACache aCache = ACache.get(context);
        aCache.put("homeBean", (Serializable) homeBaen);

    }
    public HomeBaen getHomeData(){
        ACache aCache = ACache.get(context);
        HomeBaen bean = (HomeBaen)aCache.getAsObject("homeBean");
        Log.e("##get",bean.toString());
        return bean;
    }
    public void unRegisterListener(IHomeReceivedListener receivedListener){
        if(ihListener.contains(receivedListener)){
            ihListener.remove(receivedListener);
        }
    }
    public void registerListener(IHomeReceivedListener receivedListener){
        if(ihListener.contains(receivedListener)){
            return;
        }else{
            ihListener.add(receivedListener);
        }
    }
    public interface IHomeReceivedListener{
        void HomeDataReceived(HomeBaen homeBaen);
    }


}
