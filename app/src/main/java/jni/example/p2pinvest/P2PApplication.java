package jni.example.p2pinvest;

import android.app.Application;

import jni.example.common.NetConnectManager;
import jni.example.p2pinvest.manager.CaCheManager;

public class P2PApplication extends Application {
    public static P2PApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        NetConnectManager.getInstance().init(application);
        //TODO 开启服务
        CaCheManager.getInstance().init(application);
    }
}
