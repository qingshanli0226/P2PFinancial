package com.example.p2pdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.modulebase.IBasePresenter;
import com.example.modulebase.IBaseView;
import com.example.modulecommon.AppNetConfig;
import com.example.p2pdemo.bean.HomeBean;
import com.example.p2pdemo.presenter.HomePresenter;

import java.util.List;


public class CacheService extends Service implements IBaseView<Object> {
    private IHomeDataListener iHomeDataListener;
    private IBasePresenter iHomePresenter;
    private HomeBean homeBean;



    public HomeBean getHomeDate(){
        iHomePresenter = new HomePresenter();
        iHomePresenter.attachView(this);
        iHomePresenter.doHttpRequest(AppNetConfig.HOME_REQUEST_CODE);
        return homeBean;
    }
    @Override
    public void onLoadDataSuccess(int requestCode, Object data) {
        if (requestCode == AppNetConfig.HOME_REQUEST_CODE){
            homeBean = (HomeBean) data;
            iHomeDataListener.onHomeDateRecived(homeBean);
        }
    }

    @Override
    public void onLoadDataListSuccess(int requestCode, List<Object> data) {

    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }

    //定义监听接口
    interface IHomeDataListener{
        void onHomeDateRecived(HomeBean bean);
    }
    //返回Service让其他类调用
    public class CacheBinder extends Binder{
        public CacheService getCacheService(){
            return CacheService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new CacheBinder();
    }
    //注册监听
    public void registerListener(IHomeDataListener listener)
    {
        this.iHomeDataListener = listener;
    }
    //注销监听
    public void UNRegisterListener(){
        this.iHomeDataListener = null;
    }


}
