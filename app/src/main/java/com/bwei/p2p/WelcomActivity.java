package com.bwei.p2p;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwei.base.ActivityInstanceManager;
import com.bwei.base.BaseActivity;

public class WelcomActivity extends BaseActivity {

    private static final int TO_MAIN = 1;
    private RelativeLayout rlWelcome;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    finish();
                    startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                    break;
            }

        }
    };


    protected void initView() {
        rlWelcome = (RelativeLayout) findViewById(R.id.rl_welcome);


    }

    @Override
    protected void initDate() {
        //将当前的activity添加到ActivityManager中
        ActivityInstanceManager.addActivity(this);
        //提供启动动画
        setAnimation();
        toMain();


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
