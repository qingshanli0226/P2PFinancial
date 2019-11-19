package com.example.administrator.p2pdemotext.Myapplication.Cache;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.LruCache;

import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Myapplication.ServicePackage.CacheService;

import java.util.LinkedList;
import java.util.List;

public class CacheManager  {
    private Context context;
    private CacheService cacheService;
    private List<IHomeReceivedListener> iHomeReceivedListeners=new LinkedList<>();

    //使用lrucache来做bitmap缓存,给他设定的最大缓存值是应用程序使用内存空间的八分之一
    private LruCache<String,Bitmap> bitmapLruCache=new LruCache<>((int) (Runtime.getRuntime().maxMemory()/8));

    //磁盘SD缓存
    //DiskLruCache
    private static CacheManager instance;

    public CacheManager() {
    }
    //单例
    public static  CacheManager getInstance(){
        if (instance==null){
            instance=new CacheManager();
        }
        return instance;
    }
    //定义一个布尔值
    boolean isStop=false;

    public void init(Context context) {
        this.context = context;
        Intent intent=new Intent(context,CacheService.class);
        //start绑定serviec
        context.startService(intent);
        //bind绑定service
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //实例化cacheService可以让他调用到CacheService中的方法
                cacheService = ((CacheService.CacheBinder)service).getCacheService();

                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(Bean bean) {
                        //service通知数据已经获取到
                        for (IHomeReceivedListener listener : iHomeReceivedListeners) {
                            listener.onHomeDataReceived(bean);
                        }
                        //本地存储
                        savaLocal(bean);
                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },Context.BIND_AUTO_CREATE);
    }

    private void savaLocal(Bean bean) {
        //把bean存储到本地
    }

    public interface IHomeReceivedListener{
        void onHomeDataReceived(Bean bean);
    }
    //提示储存到多少
    private void getRunningMemory() {
        Log.d("LQS: max mem = ", Runtime.getRuntime().maxMemory() / (1024*1024) + "");
    }
}

