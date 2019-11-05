package com.example.month6.view.mainactivity_frag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.diyviews.ProGrossView;
import com.example.month6.R;
import com.youth.banner.Banner;

public class Frag1 extends Fragment {
    View inflate;
    private Banner banner;
    private ProGrossView proGrossView;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            proGrossView.reFush();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag1, null);
        proGrossView=inflate.findViewById(R.id.proGrossView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (proGrossView.num>=324){
                        break;
                    }
                }
            }
        }).start();
        return inflate;
    }
}
