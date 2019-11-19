package com.example.administrator.p2pdemotext.Myapplication.ServicePackage;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Myapplication.Cache.CacheManager;


public class CacheService extends Service {
    private CacheManager.IHomeReceivedListener iHomeReceivedListener;
    public interface IHomeDataListener {
        void onHomeDataReceived(Bean bean);
    }

    public class CacheBinder extends Binder {
        public CacheService getCacheService() {
            //返回service，让其他类调用
            return CacheService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {

        return new CacheBinder();
    }
    public void registerListener(IHomeDataListener listener){
        this.iHomeReceivedListener= (CacheManager.IHomeReceivedListener) listener;
    }
    public void unRegisterListener(){
        this.iHomeReceivedListener=null;
    }
    //获取数据,要在子线程找那个数据
    public void getHomeData(){
        //获取数据成功
        Bean bean=null;
        iHomeReceivedListener.onHomeDataReceived(bean);
    }
}
