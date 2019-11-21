package com.bw.jinrong.cache;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;
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
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                cacheService = ((CacheService.CacheBinder)iBinder).getCacheService();
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(HomeBean bean) {

                    }

                    @Override
                    public void onUpdateApkBean(UpdateBean updateBean) {
                        updateManagerListener.getUpdateApkInfo(updateBean);
                        onUpdateBean = updateBean;
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

    public void isFirstApk(UpdateBean updateBean){
        if (updateBean.getApkUrl().equals(getVersion())){
            Dialog();
        }else {
            Toast.makeText(context, "当前已是最新版本", Toast.LENGTH_SHORT).show();
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
                    .setNeutralButton("不再提示", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            isLook = true;
                            updateManagerListener.toMain();
                        }
                    })

                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
    public String getVersion() {
        PackageManager packageManager = context.getPackageManager();
        String version = "未知版本";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
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

    public interface UpdateManagerListener{
        void getUpdateApkInfo(UpdateBean updateBean);
        void toMain();
    }

}
