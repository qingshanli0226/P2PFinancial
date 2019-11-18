package com.example.p2pfiancial.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.base.presenter.IBasePresenter;
import com.example.base.view.IBaseView;
import com.example.commen.P2PError;
import com.example.p2pfiancial.bean.HomeBannerBean;
import com.example.p2pfiancial.fragment.homefragment.HomePresenter;

import java.util.List;

public class CacheService extends Service implements IBaseView {
    private CacheBind netBinder = new CacheBind();
    private IHomeDataListener iHomeDataListener;
    private IBasePresenter iBasePresenter;
    final int BANNER_REQUEST_CODE = 1001;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);

        return netBinder;
    }

    //谁维护接口, 谁定义
    public interface IHomeDataListener {
        void onHomeDataReceived(HomeBannerBean bean);
    }

    public class CacheBind extends Binder {
        public CacheService getCacheService() {
            return CacheService.this;
        }
    }

    public void registerListener(IHomeDataListener listener) {
        this.iHomeDataListener = listener;
    }

    public void unRegisterListener() {
        this.iHomeDataListener = null;
    }

    //获取数据, 要在子线程进行获取
    public void getHomeData(){
        iBasePresenter.doHttpRequest(BANNER_REQUEST_CODE);

//        return bean;
    }



    @Override
    public void onHttpRequestDataSuccess(int requestCode, Object data){
        if (requestCode == BANNER_REQUEST_CODE) {
            iHomeDataListener.onHomeDataReceived((HomeBannerBean) data);
        }
    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {

    }

    @Override
    public void showLoading(int showCode) {

    }

    @Override
    public void hideLoading(int showCode) {

    }


}


