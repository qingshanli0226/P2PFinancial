package com.example.p2pdemo.Manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.LruCache;


import com.example.common.ACache;
import com.example.common.NetConnectManager;
import com.example.p2pdemo.Bean.HomeBaen;
import com.example.p2pdemo.Bean.UpdateBean;
import com.example.p2pdemo.service.CacheService;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {


    private static CacheManager cacheManager;
    private Context context;
    CacheService cacheService;
    private ACache aCache;
    HomeBaen home;

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
        Intent intent = new Intent(context,CacheService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                cacheService=((CacheService.MyBinder)service).getCacheService();
                //注册监听通知数据已获取到
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(HomeBaen homeBaen) {
                        home = homeBaen;
                        saveLocal(homeBaen);
                        for (IHomeReceivedListener listener:ihListener){
                            listener.onHomeDataReceived(homeBaen);
                        }


                    }
                    @Override
                    public void onUpdateApkBean(UpdateBean updateBean) {

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
        },Context.BIND_AUTO_CREATE);

//        aCache=ACache.get(context);


    }


    private void saveLocal(HomeBaen homeBaen){
        Log.e("#S","保存");
//        aCache.put("homeBean", homeBaen.toString());
        SharedPreferences preferences = context.getSharedPreferences("Save", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        String toJson = new Gson().toJson(homeBaen);
        edit.putString("jsonBean",toJson);
        edit.commit();


    }
    public String getHomeData(){
        SharedPreferences preferences = context.getSharedPreferences("Save", Context.MODE_PRIVATE);
        String jsonBean = preferences.getString("jsonBean", null);
        Log.e("#S","获取"+jsonBean);
//        HomeBaen homeBean =(HomeBaen) aCache.getAsObject("homeBean");
        return jsonBean;
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
        void onHomeDataReceived(HomeBaen homeBaen);
    }
    private void getRunningMemory() {
        Log.d("LQS: max mem = ", Runtime.getRuntime().maxMemory() / (1024*1024) + "");
    }

}
