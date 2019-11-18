package com.bwei.p2p;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.LruCache;

import com.bwei.base.IbaseDataCache;
import com.bwei.base.bean.Index;
import com.bwei.common.NetcomentManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CacheManager {
    private static CacheManager instance;

    private CacheManager() {}

    private MyService myService;
    private Context context;
    private static String indexPath = "/sdcard/indexData.txt";

    public static CacheManager getInstance() {
        if (instance==null){
            instance=new CacheManager();
        }
        return instance;
    }
    private IbaseDataCache ibaseDataCache;
    private GetDateListener listener;

    //使用lrucache来做bitmap的缓存。 给它设定的最大缓存值是应用程序课使用内存空间的1/8
    private LruCache<String, Bitmap> bitmap = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/8));

    public void registerGetDateListener(GetDateListener listener){
        this.listener=listener;
    }

    public void unRegisterGetDateListener(){
        this.listener=null;
    }
    public void init(final Context context){
        this.context=context;
        Intent intent = new Intent();
        intent.setClass(context, MyService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = ((MyService.LocalBinder)service).getService();
                myService.registerListener(new IbaseDataCache() {
                    @Override
                    public void onGetDataSucess(Index data) {
                        Log.i("ssssss", "onGetDataSucess: 服务下载数据");
                        writeObject(data);
                        Log.i("llll", "onGetDataSucess: "+data);
                        if(listener!=null){
                            listener.getIndex(data);
                        }
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

    private static void writeObject(Index data) {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(indexPath));
            oos = new ObjectOutputStream(fos);
            Log.d("ssssss","写入SD卡");
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public Object getInsexData() {
        return readObject(indexPath);
    }

    private Object readObject(String indexPath) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(indexPath);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    public interface GetDateListener{
        void getIndex(Index index);
    }

}
