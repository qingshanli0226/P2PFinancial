package com.example.month6.view.activirys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.common.diyviews.singleclass.ActivityManager;
import com.example.month6.R;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {
    //语言 尺寸 分辨率 版本 横竖屏 屏幕
    @BindView(R.id.welBack)
    RelativeLayout welBack;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //顶部状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backAlpha();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activit_ywel;
    }

    private void backAlpha() {
        //设置透明动画
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                ActivityManager.getInstance().removeActivity(WelcomeActivity.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        welBack.setAnimation(animation);
    }

    //横竖屏监听
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //竖屏
        if (newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){

        }else {

        }
    }
}
