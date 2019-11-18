package com.example.p2pfiancial.cache;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.commen.NetConnectManager;
import com.example.p2pfiancial.bean.HomeBannerBean;
import com.example.p2pfiancial.service.CacheService;

import java.util.LinkedList;

public class CacheManager {
    private static CacheManager instance;
    private Context mContext;
    private CacheService cacheService;
    private HomeBannerBean homeData;
    private Intent intent;
    private ACache aCache;

    //单例
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }


    //服务连接
    private ServiceConnection serviceConnection = new ServiceConnection() {
        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            cacheService = ((CacheService.CacheBind) service).getCacheService();

            Log.i("TAG", "onServiceConnected: 服务已连接");

            //注册网络连接判断
            NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                @Override
                public void onConnected() {
                    Log.i("TAG", "onConnected: 网络连接");
                    cacheService.getHomeData();
                }

                @Override
                public void onDisConnected() {
                    Log.i("TAG", "onConnected: 网络未连接");
                }
            });

            //注册监听
            cacheService.registerListener(new CacheService.IHomeDataListener() {
                @Override
                public void onHomeDataReceived(HomeBannerBean bean) {
                    homeData = bean;
                    //本地存储
                    saveLocal(bean);
                    //service 通知数据已经获取到
                    for (IHomeReceivedListener listener : iHomeReceivedListeners) {
                        listener.onHomeDataReceived(getHomeData());
                    }
                }
            });
        }

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //初始化
    public void init(Context context) {
        this.mContext = context;
        intent = new Intent(context, CacheService.class);
        //开启服务
        context.startService(intent);
        //绑定服务
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        aCache = ACache.get(mContext);
    }

    private LinkedList<IHomeReceivedListener> iHomeReceivedListeners = new LinkedList<>();


    public HomeBannerBean getHomeData() {
        if (homeData == null) {
            //本地缓存
            //没有网络从本地获取数据, 将数据返回
            homeData = (HomeBannerBean) aCache.getAsObject("home");
            //Log.i("TAG", "getHomeData: 读取缓存" + homeData.toString());
            return homeData;
        }

        return homeData;
    }

    private void saveLocal(HomeBannerBean bean) {
        //把bean存储到本地
        aCache.put("home", bean);
    }

    //注册监听
    public void registerIHomeReceivedListener(IHomeReceivedListener iHomeReceivedListener) {
        if (iHomeReceivedListeners.contains(iHomeReceivedListener)) {
            return;
        } else {
            iHomeReceivedListeners.add(iHomeReceivedListener);
        }
    }

    //注销监听
    public void unRegisterIHomeReceivedListener(IHomeReceivedListener iHomeReceivedListener) {
        if (iHomeReceivedListeners.contains(iHomeReceivedListener)) {
            iHomeReceivedListeners.remove(iHomeReceivedListener);
        }
    }

    //谁维护接口, 谁定义
    public interface IHomeReceivedListener {
        void onHomeDataReceived(HomeBannerBean homeBannerBean);
    }

    public void closeCache() {
        iHomeReceivedListeners.remove();
        cacheService.unRegisterListener(); //关闭监听
        cacheService.unbindService(serviceConnection);//解绑
        cacheService.stopService(intent); //停止服务
    }
}
