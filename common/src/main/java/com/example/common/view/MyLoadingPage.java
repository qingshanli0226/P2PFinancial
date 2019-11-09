package com.example.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.common.MyApplication;
import com.example.common.R;

public class MyLoadingPage extends LinearLayout {

    private Context context;
    private ImageView iv_image;
    private TextView tv_loadtext;
    private StartThread startThread;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    startAnimation();
                    break;
                case 200:
                    setLoadingText(1);
                    break;
            }
        }
    };

    public MyLoadingPage(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public MyLoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public MyLoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_loading,this);
        iv_image = findViewById(R.id.iv_image);
        tv_loadtext = findViewById(R.id.tv_loadtext);
    }

    public void startLoadingAnimation(){
        this.setVisibility(VISIBLE);
        startThread = new StartThread();
        startThread.flag2 = 0;
        startThread.time = 0;
        startThread.start();
    }

    public void setLoadingPagedismiss(){
        this.setVisibility(GONE);
    }

    public void interruptLoadingAnimation(){
        startThread.flag2 = 1;
        startThread.interrupt();
    }

    protected void setLoadingText(int flag){
        switch (flag){
            case 1:tv_loadtext.setText("加载数据失败");
        }
    }

    protected void startAnimation(){
        iv_image.startAnimation(MyApplication.getInstance().getRotateAnimaiton());
    }

    public class StartThread extends Thread {

        public int flag2 = 0;
        int time = 0;

        @Override
        public void run() {
            super.run();

            while (flag2==0){
                time+=2;
                Log.e("####","+1");

                if(time==6){
                   flag2 = 1;
                   handler.sendEmptyMessage(200);
                }else {
                    handler.sendEmptyMessage(100);
                }
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
