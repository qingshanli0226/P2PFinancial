package com.example.p2pdemo;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import com.example.common.NetConnectManager;
import com.example.p2pdemo.Bean.HomeBaen;
import com.example.p2pdemo.Bean.UpdateBean;

public class UpdateApkManager {

    public static UpdateApkManager updateManager;
    private Context context;
    private CacheService updateService;
    private UpdateManagerListener updateManagerListener;
    private UpdateBean updateBean;
    private String isCome="1";

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
        //绑定服务
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                updateService=((CacheService.MyBinder)service).getCacheService();
                updateService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(HomeBaen homeBaen) {

                    }

                    @Override
                    public void onUpdateApkBean(UpdateBean updateBeans) {
                        updateManagerListener.getUpdateApkInfo(updateBeans);
                        updateBean=updateBeans;
                    }
                });

                if(!NetConnectManager.getInstance().isConnectStatus()){
                    return;
                }else{
                    updateService.getUpdateBean();
                }
                NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        updateService.getUpdateBean();
                    }

                    @Override
                    public void onDisConnected() {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }


    public String getCom(){
        SharedPreferences preferences = context.getSharedPreferences("Com", Context.MODE_PRIVATE);
        String com = preferences.getString("isCom", null);
        return com;

    }
    public void IsCom(String a){
        SharedPreferences preferences = context.getSharedPreferences("Com", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("isCom",a);
        edit.commit();
    }
    //下载APK
    public void InstallApk() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(updateBean.getApkUrl()));
        //设置只有Wife网络都可以下载
        request.setAllowedNetworkTypes(DownloadManager.PAUSED_QUEUED_FOR_WIFI);
        request.setTitle("下载中");
        //漫游不下载
        request.setAllowedOverRoaming(false);
        DownloadManager systemService =(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        systemService.enqueue(request);

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


    //注册监听

    public void registerUpApkListener(UpdateManagerListener updateManagerListener) {
        this.updateManagerListener = updateManagerListener;

    }
    //注销监听
    public void unregisterUpApkListener() {
        this.updateManagerListener = null;
    }

    public interface UpdateManagerListener {
        void getUpdateApkInfo(UpdateBean updateBean);
    }

}
