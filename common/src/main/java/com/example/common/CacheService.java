package com.example.common;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.common.Bean.HomeBaen;

public class CacheService extends Service {


    private IHomeDataListener iHomeDataListener;
    public interface IHomeDataListener{
        void HomeDataReceived(HomeBaen homeBaen);
    }
    class MyBinder extends Binder{
        public CacheService getCacheService(){
            return CacheService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void registerListener(IHomeDataListener dataListener){
        this.iHomeDataListener=dataListener;
    }
    public void unregisterListener(){
        this.iHomeDataListener=null;
    }

    public void getData(){
        HomeBaen homeBaen=null;
        iHomeDataListener.HomeDataReceived(homeBaen);
    }




}
