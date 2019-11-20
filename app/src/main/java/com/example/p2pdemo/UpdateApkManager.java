package com.example.p2pdemo;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import com.example.p2pdemo.Bean.HomeBaen;
import com.example.p2pdemo.Bean.UpdateBean;

public class UpdateApkManager {

    public static UpdateApkManager updateManager;
    private Context context;
    private CacheService updateService;
    private UpdateManagerListener updateManagerListener;
    private UpdateBean updateBean;
    private boolean isOverLook=false;

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
                updateService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void HomeDataReceived(HomeBaen homeBaen) {

                    }

                    @Override
                    public void UpdateApkBean(UpdateBean updateBeans) {
                        updateBean=updateBeans;
                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        //绑定服务
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    //是否是最新版本
    public void isFirstApk(){
        if(updateBean.getVersion().equals(getVersion())){
            AlerDialog();
        }else{
            Toast.makeText(context, "当前已近是最新版本啦", Toast.LENGTH_SHORT).show();
        }
    }
    //对话框
    public void AlerDialog(){
        if(isOverLook==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("是否更新!");
            builder.setTitle("版本");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    InstallApk();
                }
            });
            builder.setNeutralButton("不再提示!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    isOverLook=true;
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }

    }
    //下载APK
    public void InstallApk() {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(updateBean.getApkUrl()));
        //任何网络都可以下载
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

    //获取数据
    public UpdateBean getUpdateInfo() {
        return updateBean;
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
        void getUpdateApkInfo(CacheService updateBean);
    }

}
