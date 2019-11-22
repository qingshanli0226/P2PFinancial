package com.bwei.p2p;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.base.ActivityInstanceManager;
import com.bwei.base.BaseActivity;
import com.bwei.base.bean.Index;
import com.bwei.base.bean.UpdateInfo;

import java.io.File;

public class WelcomeActivity extends BaseActivity {

    private static final int TO_MAIN = 1;
    private RelativeLayout rlWelcome;
    private TextView tvWelcomeVersion;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    finish();
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    break;
            }

        }
    };


    protected void initView() {
        rlWelcome = (RelativeLayout) findViewById(R.id.rl_welcome);
        tvWelcomeVersion = (TextView) findViewById(R.id.tv_welcome_version);
    }

    @Override
    protected void initDate() {
        //将当前的activity添加到ActivityManager中
        ActivityInstanceManager.addActivity(this);
        //提供启动动画
        setAnimation();
        boolean connected = isConnected();
        if (connected){
            //有网络
            CacheManager.getInstance().registerGetDateListener(new CacheManager.onGetDateListener() {
                @Override
                public void onGetData(Index index) {
                }

                @Override
                public void onGetUpDate(UpdateInfo upDate) {
                    //更新信息
                    String version = getVersion();
                    tvWelcomeVersion.setText(version);
                    //比较服务器获取的最新的版本跟本应用的版本是否一致
                    if(version.equals(upDate.version)){
                        Toast.makeText(WelcomeActivity.this, "当前应用已经是最新版本", Toast.LENGTH_SHORT).show();
                        toMain();
                    }else{
                        new AlertDialog.Builder(WelcomeActivity.this)
                                .setTitle("下载最新版本")
                                .setMessage(upDate.desc)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //下载服务器保存的应用数据
                                        downloadApk();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        toMain();
                                    }
                                })
                                .show();
                    }
                }
            });
        }else {
            Toast.makeText(this,"当前没有网络连接",Toast.LENGTH_SHORT).show();
        }
        toMain();


    }
    private ProgressDialog dialog;
    private File apkFile;
    private void downloadApk() {
        //初始化水平进度条的dialog
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
        //初始化数据要保持的位置
        File filesDir;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filesDir = this.getExternalFilesDir("");
        }else{
            filesDir = this.getFilesDir();
        }
        apkFile = new File(filesDir,"update.apk");
    }

    private String getVersion() {
        String version="未知版本";
        PackageManager packageName = getPackageManager();
        try {
            PackageInfo packageInfo = packageName.getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
            Log.i("SSSSS", "getVersion: "+version);

        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return version;

    }

    @Override
    protected int getLayoutId() {
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_welcome;
    }
    private void toMain() {
        long currentTime = System.currentTimeMillis();
        handler.sendEmptyMessageDelayed(TO_MAIN, 3000);
    }
    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//0：完全透明  1：完全不透明
        alphaAnimation.setDuration(3000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());//设置动画的变化率

        //启动动画
        rlWelcome.startAnimation(alphaAnimation);

    }


    @Override
    public void onConnected() {
        if (!isConnected()) {
            Toast.makeText(this, "当前网络没有连接", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "当前网络连接正常，获取数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnected() {
        Toast.makeText(this,"当前网络没有连接",Toast.LENGTH_SHORT).show();

    }
}
