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

    private static final int TO_MAIN = 1;
    private TextView tv_welcome_version;
    private RelativeLayout rl_welcome;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==TO_MAIN){
                finish();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };
  //  @Override
 //   protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
     //   setContentView(R.layout.activity_welcome);

     //   initView();

   // }

    @Override
    protected void initListener() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initdata() {
        tv_welcome_version = (TextView) findViewById(R.id.tv_welcome_version);
        rl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        rl_welcome.startAnimation(alphaAnimation);



        handler.sendEmptyMessageDelayed(TO_MAIN, 3000);
    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_welcome;
    }

}
