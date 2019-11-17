package com.example.p2invest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.base.IBHomeData;
import com.example.base.IBaseView;
import com.example.base.IBsePresenter;
import com.example.net.BannerData;
import com.example.p2invest.presenter.HomePresenter;

import java.util.List;

public class MyService extends Service  {
    private IBsePresenter iBsePresenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder()  ;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        iBsePresenter=new HomePresenter();
    }

    public void registListener(IBHomeData data){
        Log.i("wzy", "registListener: ");
        Log.i("wzy", "registListener: "+data);
        iBsePresenter.addListener(data);
    }

    public  class MyBinder extends Binder{
        public  MyService getService(){
            return MyService.this;
        }
    }


    public void getDate(){
        iBsePresenter.getData();
    }
}
