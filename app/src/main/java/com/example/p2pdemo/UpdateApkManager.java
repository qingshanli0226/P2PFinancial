package com.example.p2pdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;

import com.example.p2pdemo.Bean.UpdateBean;

public class UpdateApkManager {

    public static UpdateApkManager updateManager;
    private Context context;
    private CacheService updateService;
    private UpdateManagerListener updateManagerListener;
    private UpdateBean updateBean;

    public UpdateApkManager() {
    }

    public static UpdateApkManager getInstance() {
        if (updateManager == null) {
            updateManager = new UpdateApkManager();
        }
        return updateManager;
    }

    public void init(Context context) {
        this.context = context;
        Intent intent = new Intent(context, CacheService.class);
        context.startService(intent);
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                updateService = ((CacheService.MyBinder) service).getCacheService();


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    //下载APK
    public void InstallApk() {

    }

    //获取当前版本
    public String getVersion() {
        PackageManager packageManager = context.getPackageManager();
        String version = "未知";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    //获取数据
    public UpdateBean getUpdateInfo() {
        return updateBean;
    }

    public void registerUpApkListener(UpdateManagerListener updateManagerListener) {
        this.updateManagerListener = updateManagerListener;

    }

    public void unregisterUpApkListener() {
        this.updateManagerListener = null;
    }

    public interface UpdateManagerListener {
        void getUpdateApkInfo(CacheService updateBean);
    }

}
