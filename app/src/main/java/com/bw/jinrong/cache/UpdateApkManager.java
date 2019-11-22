package com.bw.jinrong.cache;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.bw.common.NetConnectManager;
import com.bw.jinrong.bean.HomeBean;
import com.bw.jinrong.bean.UpdateBean;
import com.bw.jinrong.service.CacheService;

public class UpdateApkManager {

    public static UpdateApkManager updateApkManager;
    private Context context;
    private CacheService cacheService;
    private UpdateManagerListener updateManagerListener;
    private UpdateBean onUpdateBean;
    private boolean isLook = false;

    private ServiceConnection serviceConnection;

    public UpdateApkManager() {
    }

    public static UpdateApkManager getInstance() {

        if (updateApkManager == null){
            updateApkManager = new UpdateApkManager();
        }

        return updateApkManager;
    }

    public void init(Context context){
        this.context = context;
        Intent intent = new Intent(context,CacheService.class);
        context.startService(intent);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                cacheService = ((CacheService.CacheBinder)iBinder).getCacheService();
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(HomeBean bean) {

                    }

                    @Override
                    public void onUpdateApkBean(UpdateBean updateBean) {
                        Log.d("xxx", "onUpdateApkBean: " + updateBean.getApkUrl());
                        updateManagerListener.getUpdateApkInfo(updateBean);
                        onUpdateBean = updateBean;
                    }
                });

                if (!NetConnectManager.getInstance().isConnectStatus()){
                    return;
                }else {
                    cacheService.getUpdate();
                }

                NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        cacheService.getUpdate();
                    }

                    @Override
                    public void onDisConnected() {

                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        //绑定服务
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    public void isFirstApk(){
        Log.d("xxx", "isFirstApk: " + getOnUpdateBean().getVersion());
        if (getVersions().equals(getOnUpdateBean().getVersion())){
            Toast.makeText(context, "当前已是最新版本", Toast.LENGTH_SHORT).show();
            updateManagerListener.toMain();
        }else {
            Dialog();
        }
    }

    private void Dialog() {
        if (!isLook){
            new AlertDialog.Builder(context)
                    .setMessage("是否更新最新版本")
                    .setTitle("提示更新版本")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            downloadApk();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            updateManagerListener.toMain();
                        }
                    })
                    .setNeutralButton("不再提示", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            isLook = true;
                            updateManagerListener.toMain();
                        }
                    })
                    .create()
                    .show();
        }
    }

    //下载apk
    private void downloadApk() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(onUpdateBean.getApkUrl()));
        //下载
        request.setAllowedNetworkTypes(DownloadManager.PAUSED_QUEUED_FOR_WIFI);
        request.setTitle("下载apk中");
        request.setAllowedOverRoaming(false);
        DownloadManager systemService = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        systemService.enqueue(request);
    }

    //获取当前版本
    public String getVersions() {
        PackageManager packageManager = context.getPackageManager();
        String version = "未知版本";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
            Log.d("xxx", "getVersion: " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    //获取数据
    public UpdateBean getOnUpdateBean(){
        return onUpdateBean;
    }

    //注册监听
    public void registerUpdateApkListener(UpdateManagerListener updateManagerListener){
        this.updateManagerListener = updateManagerListener;
    }

    //注销监听
    public void unRegisterUpdateApkListener(){
        this.updateManagerListener = null;
    }

    //关闭服务
    public void unBindService(){
        context.unbindService(serviceConnection);
    }

    public interface UpdateManagerListener{
        void getUpdateApkInfo(UpdateBean updateBean);
        void toMain();
    }

}
