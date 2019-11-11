package com.example.p2invest.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.base.BaseActivity;
import com.example.p2invest.R;

public class WelcomeActivity extends BaseActivity {

    private static final int TOMAIN = 1;
    private TextView tvWelcomeVersion;
    private RelativeLayout reWelcome;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==TOMAIN){
                finish();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void initListener() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initdata() {
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        reWelcome.startAnimation(alphaAnimation);



        handler.sendEmptyMessageDelayed(TOMAIN, 3000);
    }

    @Override
    protected void initview() {
        tvWelcomeVersion = (TextView) findViewById(R.id.tvwelcomeversion);
        reWelcome = (RelativeLayout) findViewById(R.id.rlwelcome);

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_welcome;
    }

}
