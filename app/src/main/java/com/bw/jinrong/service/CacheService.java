package com.bw.jinrong.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.bw.jinrong.bean.HomeBean;

public class CacheService extends Service {

    private IHomeDataListener iHomeDataListener;

    //谁维护接口，谁定义
    public interface IHomeDataListener{
        void onHomeDataReceived(HomeBean bean);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new CacheBinder();
    }

    public class CacheBinder extends Binder{
        public CacheService getCacheService(){
            return CacheService.this;
        }
    }

    public void registerListener(IHomeDataListener listener){
        this.iHomeDataListener = listener;
    }

    public void unRegisterListener(){
        this.iHomeDataListener = null;
    }

    //获取数据
    public void getHomeData(){
        //获取数据成功
        HomeBean bean = null;
        //获取到数据，去通知Manager
        iHomeDataListener.onHomeDataReceived(bean);
    }

}
