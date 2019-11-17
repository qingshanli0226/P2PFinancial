package com.example.p2invest.manager;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.LruCache;

import com.example.base.IBHomeData;
import com.example.common.NetConnectManager;
import com.example.net.BannerData;
import com.example.p2invest.service.MyService;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    public static CacheManager cacheManager;
    private Context context;
    private boolean isStop=false;
    private MyService myService;
    private List<IHomeReceiveListener> iHomeDtaListeners=new LinkedList<>();

    public CacheManager(Context context) {
        this.context = context;
    }

    public static  CacheManager getInstance(Context ctx){
        if (cacheManager==null){
            cacheManager=new CacheManager(ctx);
        }
        return cacheManager;
    }
    public void init(){
        Intent intent = new Intent(context,MyService.class);
        context.startService(intent);

         context.bindService(intent, new ServiceConnection() {
             @Override
             public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                  myService=((MyService.MyBinder)iBinder).getService();

                  myService.registListener(new IBHomeData() {
                      @Override
                      public void getHomeDta(String json) {
                          BannerData data = new Gson().fromJson(json, BannerData.class);
                          Log.i("getHomeDta", "getHomeDta: "+json);
                          saveLocal(json);
                          for (IHomeReceiveListener listener:iHomeDtaListeners) {
                                listener.onHomeDataReceived(data);
                          }
                      }
                  });

                  if (!NetConnectManager.getInstance().isConnectStatus()){
                      return;
                  }else {
                      myService.getDate();
                  }
                  NetConnectManager.getInstance().registINetListener(new NetConnectManager.INetListener() {
                      @Override
                      public void onConnected() {
                          myService.getDate();
                      }

                      @Override
                      public void onDisConnected() {

                      }
                  });
             }

             @Override
             public void onServiceDisconnected(ComponentName componentName) {

             }
         },Context.BIND_AUTO_CREATE);
    }
    public void unregistReceive(IHomeReceiveListener homeReceiveListener){
        if (!iHomeDtaListeners.contains(homeReceiveListener)){
            return;
        }else {
            iHomeDtaListeners.remove(homeReceiveListener);
        }
    }
    public void registReceive(IHomeReceiveListener homeReceiveListener){
        if (!iHomeDtaListeners.contains(homeReceiveListener)){
            iHomeDtaListeners.add(homeReceiveListener);
        }
    }

    @SuppressLint("CommitPrefEdits")
    private void saveLocal(String bannerData){
        SharedPreferences wzy = context.getSharedPreferences("wzy", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = wzy.edit();
        edit.putString("json",bannerData);

        edit.commit();
    }
    public Object readLocal(){
        SharedPreferences wzy = context.getSharedPreferences("wzy", Context.MODE_PRIVATE);
        String json = wzy.getString("json", "");
        BannerData data = new Gson().fromJson(json, BannerData.class);
        return  data;
    }


    public interface IHomeReceiveListener{
        void onHomeDataReceived(BannerData data);
    }
    public BannerData getBannerDta(){
        return (BannerData) readLocal();
    }

}
