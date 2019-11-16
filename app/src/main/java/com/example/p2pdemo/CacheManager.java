package com.example.p2pdemo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.modulecommon.Constructor;
import com.example.modulecommon.manager.NetConnetMannager;
import com.example.p2pdemo.bean.HomeBean;

import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.List;

//数据缓存管理类
public class CacheManager {
    private static CacheManager instace;
    private List<IHomeReceivedListener> iHomeReceivedListeners = new LinkedList<>();
    private HomeBean homeBean;
    private Context context;
    private ACache mAcache;//缓存
    private CacheService cacheService;
    private CacheManager(){}
    public static CacheManager getInstance(){
        if (instace == null)
            instace = new CacheManager();
        return instace;
    }
    public void init(Context context){
        this.context = context;
        mAcache = ACache.get(context);
        Intent intent = new Intent();
        intent.setClass(context,CacheService.class);

        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                cacheService = ((CacheService.CacheBinder)service).getCacheService();
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDateRecived(HomeBean bean) {
                        //service通知数据已经获取到
                        for (IHomeReceivedListener listener : iHomeReceivedListeners) {
                             listener.onHomeDataReceived(bean);
                        }
                        if (bean!=null)
                        saveLocal(bean);
                    }
                });
                if (!NetConnetMannager.getInstance().isConnectStatus()){
                    return;
                }else {
                    cacheService.getHomeDate();
                }
                NetConnetMannager.getInstance().registerNetConnectListener(new NetConnetMannager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        cacheService.getHomeDate();
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
    private void saveLocal(HomeBean bean) {
        //把Bean存储到ACache
          mAcache.put(Constructor.KEY_HOME_DATA,bean);
    }
    //获取缓存的bean
        public HomeBean getHomeBean(){
           mAcache = ACache.get(context);
            HomeBean acache = (HomeBean) mAcache.getAsObject(Constructor.KEY_HOME_DATA);
            return acache;
        }
    public void unregisterListener(IHomeReceivedListener iHomeReceivedListener){
        if (iHomeReceivedListeners.contains(iHomeReceivedListener)){
            iHomeReceivedListeners.remove(iHomeReceivedListener);
        }
    }
    public void registerListener(IHomeReceivedListener iHomeReceivedListener){
        if (iHomeReceivedListeners.contains(iHomeReceivedListener))
            return;
        else
            iHomeReceivedListeners.add(iHomeReceivedListener);
    }
   public interface IHomeReceivedListener{
        void onHomeDataReceived(HomeBean homeBean);
    }
}
