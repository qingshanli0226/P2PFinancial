package com.bwei.base;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.LruCache;

import com.bwei.common.NetcomentManager;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    private CacheManager instance;
    private MyService myService;
    private Context context;

    public CacheManager getInstance() {
        if (instance==null){
            instance=new CacheManager();
        }
        return instance;
    }
    boolean isStop=false;
    private List<MyService.OnItemClickListener> iListeners = new LinkedList<>();

    //使用lrucache来做bitmap的缓存。 给它设定的最大缓存值是应用程序课使用内存空间的1/8
    private LruCache<String, Bitmap> bitmap = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/8));

    public void init(final Context context){
        this.context=context;
        Intent intent = new Intent();
        intent.setClass(context, MyService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = ((MyService.LocalBinder)service).getService();
                myService.registerListener(new MyService.OnItemClickListener() {
                    @Override
                    public void onDataReceived(String json) {
                        for (int i = 0; i < iListeners.size(); i++) {
                            iListeners.get(i).onDataReceived(json);
                        }

                        //本地存储
                    }

                });


                if (!NetcomentManager.getInstance(context).isConnectStatus()) {
                    return;
                } else {
                    myService.getData();
                }

                NetcomentManager.getInstance(context).registerNetConnectListener(new NetcomentManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        myService.getData();
                    }

                    @Override
                    public void onDisConnected() {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },Context.BIND_AUTO_CREATE);


    }

}
