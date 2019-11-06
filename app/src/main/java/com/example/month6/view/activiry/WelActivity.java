package com.example.month6.view.activiry;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.month6.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelActivity extends BaseActivity {

    @BindView(R.id.welBack)
    RelativeLayout welBack;

    @Override
    protected void initData() {
        //顶部状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backAlpha();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.wel_activity;
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
                Intent intent = new Intent(WelActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        welBack.setAnimation(animation);
    }

}
