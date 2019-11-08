package com.bw.jinrong.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bw.jinrong.R;
import com.bw.view.RoundProgress;
import com.youth.banner.Banner;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    private View view;
    private ImageView iv_title_back;
    private TextView tv_title;
    private ImageView iv_title_setting;
    private Banner banner;
    private TextView tv_home_product;
    private RoundProgress roundPro_home;
    private TextView tv_home_yearrate;

    private int currentProgress;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            roundPro_home.setMax(100);
            for (int i = 0; i < currentProgress; i++){
                roundPro_home.setProgress(i + 1);

                SystemClock.sleep(20);
                //强制重绘
                //主线程、分线程都可以如此调用
                roundPro_home.postInvalidate();
            }
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        currentProgress = 90;
        new Thread(runnable).start();
    }

    private void initView(View view) {
        iv_title_back = (ImageView) view.findViewById(R.id.iv_title_back);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_title_setting = (ImageView) view.findViewById(R.id.iv_title_setting);
        banner = (Banner) view.findViewById(R.id.banner);
        tv_home_product = (TextView) view.findViewById(R.id.tv_home_product);
        roundPro_home = (RoundProgress) view.findViewById(R.id.roundPro_home);
        tv_home_yearrate = (TextView) view.findViewById(R.id.tv_home_yearrate);

        iv_title_back.setVisibility(View.GONE);
        tv_title.setText("首页");
        iv_title_setting.setVisibility(View.GONE);
    }

//    private class GlideImageLoader extends ImageLoader {
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//            Glide.with(context).load((String) path).into(imageView);
//        }
//    }
}