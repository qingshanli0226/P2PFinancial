package com.example.administrator.p2pdemotext.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.R;

import java.util.Timer;
import java.util.TimerTask;

public class GuildeActivity extends BaseActivity {
    private ImageView guildeMainImgui;


    int i=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guilde);

        guildeMainImgui = (ImageView) findViewById(R.id.guilde_main_imgui);
        AlphaAnimation animation=new AlphaAnimation(0.2f,1f);
        animation.setDuration(2000);
        animation.start();
        guildeMainImgui.startAnimation(animation);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i>=0){
                 i--;
                }else {
                    Intent intent=new Intent(GuildeActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        },0,1000);
    }
}
