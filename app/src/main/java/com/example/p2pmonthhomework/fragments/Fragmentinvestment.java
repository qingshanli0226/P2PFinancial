package com.example.p2pmonthhomework.fragments;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.base.BaseFragment;
import com.example.common.view.MyTitlebar;
import com.example.p2pmonthhomework.MyApplication;
import com.example.p2pmonthhomework.R;

import org.jetbrains.annotations.NotNull;

public class Fragmentinvestment extends BaseFragment {

    private MyTitlebar mtitlebar;

    private ImageView iv_image;
    private TextView tv_loadtext;
    private View layout_load;

    private StartThread startThread;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    iv_image.startAnimation(MyApplication.getInstance().getRotateAnimaiton());
                    break;
                case 200:
                    tv_loadtext.setText("加载数据失败");
                    startThread.interrupt();
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.fragment_investment;
    }

    @Override
    public void initView(@NotNull View view) {
        layout_load = view.findViewById(R.id.layout_load);
        iv_image = view.findViewById(R.id.iv_image);
        tv_loadtext = view.findViewById(R.id.tv_loadtext);
        mtitlebar = view.findViewById(R.id.mtitlebar);

        setTitlebar();
    }

    private void setTitlebar() {
        mtitlebar.setTitle("投资");
    }


    @Override
    public void initData() {
        startThread = new StartThread();

        startThread.flag2 = 0;
        startThread.time = 0;

        startThread.start();
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
