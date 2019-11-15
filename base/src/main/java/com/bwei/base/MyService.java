package com.bwei.base;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class MyService extends Service {
    private CacheManager cacheManager;
    public  final IBinder mBinder=new LocalBinder();
    public class LocalBinder extends Binder {
     // 在Binder中定义一个自定义的接口用于数据交互
          // 这里直接把当前的服务传回给宿主
               public MyService getService(){
                  return MyService.this;
                }
     }
    //这里，我们定义一个接口
    public interface OnItemClickListener {
        public void onDataReceived(String json);
    }
    private OnItemClickListener mListener;

    //写一个设置接口监听的方法
    public void registerListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public void unRegisterListener() {
        this.mListener = null;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public void getData(){

    }
}
