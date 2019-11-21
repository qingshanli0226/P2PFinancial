package com.example.p2pmonthhomework.service;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.common.ErrorCodes;
import com.example.common.view.MyLoadingPage;
import com.example.p2pmonthhomework.HomePresenter;
import com.example.p2pmonthhomework.bean.HomeBean;

import java.util.List;

public class CacheService extends Service implements IBaseView<HomeBean> {

    private IHomeDataListener iHomeDataListener;

    private final int HOME_REQUEST_CODE = 100;

    private IBasePresenter iBasePresenter;

    @Override
    public IBinder onBind(Intent intent) {
        return new CacheBinder();
    }

    public void registerListener(IHomeDataListener listener) {
        this.iHomeDataListener = listener;
    }

    public void unRegisterListener() {
        this.iHomeDataListener = null;
    }

    public void getHomeData() {
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData(HOME_REQUEST_CODE);
    }

    @Override
    public void onGetDataSuccess(int requestCode, HomeBean data) {
        iHomeDataListener.onHomeDataReceived(data);
    }

    @Override
    public void onGetDataListSuccess(int requestCode, List<HomeBean> data) {

    }

    @Override
    public void onGetDataFailed(int requestCode, ErrorCodes codes) {

    }

    public interface IHomeDataListener {
        void onHomeDataReceived(HomeBean bean);
    }

    public class CacheBinder extends Binder {
        public CacheService getCacheService() {
            return CacheService.this;
        }
    }
}
