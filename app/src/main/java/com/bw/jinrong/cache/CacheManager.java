package com.bw.jinrong.cache;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.LruCache;
import com.bw.common.MyCaChe;
import com.bw.common.NetConnectManager;
import com.bw.jinrong.bean.HomeBean;
import com.bw.jinrong.bean.UpdateBean;
import com.bw.jinrong.service.CacheService;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {

    private Context context;
    private CacheService cacheService;

    private List<homeReceivedListener> iHomeReceivedListeners = new LinkedList<>();

    //使用lruCache来做bitmap的缓存，给它设定的最大缓存值是应用程序可使用内存空间的1/8
    private LruCache<String, Bitmap> bitmapLruCache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 8));

    private static CacheManager instance;

    public CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;

        Intent intent = new Intent(context,CacheService.class);

        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                cacheService=((CacheService.CacheBinder)iBinder).getCacheService();
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(HomeBean bean) {
                        //service通知数据已经获取到
                        for (homeReceivedListener listener : iHomeReceivedListeners){
                            listener.onHomeDataReceived(bean);
                        }

                        //本地存储
                        saveLocal(bean);
                    }

                    @Override
                    public void onUpdateApkBean(UpdateBean updateBean) {
                        //service通知数据已经获取到
                        for (homeReceivedListener listener : iHomeReceivedListeners){
                            listener.onHomeUpdateReceived(updateBean);
                        }
                    }
                });

                if (!NetConnectManager.getInstance().isConnectStatus()){
                    return;
                }else {
                    cacheService.getHomeData();
                }

                NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        cacheService.getHomeData();
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

    private void saveLocal(HomeBean bean) {
        //把bean存储到本地
        MyCaChe myCaChe = MyCaChe.get(context);
        myCaChe.put("bean",bean.toString());
    }

    public HomeBean getHomeData(){
       MyCaChe myCaChe = MyCaChe.get(context);
       HomeBean bean = (HomeBean) myCaChe.getAsObject("bean");
       return bean;
    }

    public void unregisterListener(homeReceivedListener listener){
        if (iHomeReceivedListeners.contains(listener)){
            iHomeReceivedListeners.remove(listener);
        }
    }

    public void registerListener(homeReceivedListener listener){
        if (iHomeReceivedListeners.contains(listener)){
            return;
        }else {
            iHomeReceivedListeners.add(listener);
        }
    }

    public interface homeReceivedListener{
        void onHomeDataReceived(HomeBean bean);
        void onHomeUpdateReceived(UpdateBean updateBean);
    }

    private void getRunningMemory(){

    }

}
