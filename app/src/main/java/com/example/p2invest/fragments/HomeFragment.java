package com.example.p2invest.fragments;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.base.BaseFragment;
import com.example.base.IBaseView;
import com.example.base.IBsePresenter;
import com.example.net.AppNetConfig;
import com.example.net.BannerData;
import com.example.p2invest.BannerLoader;
import com.example.p2invest.custor.MyProgress;
import com.example.p2invest.R;
import com.example.p2invest.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends BaseFragment implements IBaseView<BannerData> {
    private TextView tvtTitle;
    private ImageView ivTitleSetting;
    private Banner banner;
    private View loadingPage;
    private View errorPage;
    private MyProgress myProgress;
    private RelativeLayout.LayoutParams params;
    private RelativeLayout home;
    private ArrayList<String> imgUrls;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                myProgress.setProgress();
            }
        }
    };

    private IBsePresenter iBsePresenter;


    @Override
    public void initData() {
        iBsePresenter = new HomePresenter();
        iBsePresenter.attachView(this);
        iBsePresenter.getData();

        MyThreadStart();

        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initView() {
        myProgress=view.findViewById(R.id.my);
        tvtTitle=view.findViewById(R.id.tvtitle);
        home=view.findViewById(R.id.homeline);
        ivTitleSetting=view.findViewById(R.id.ivtitlesetting);
        banner=view.findViewById(R.id.banner);
        loadingPage = LayoutInflater.from(getActivity()).inflate(R.layout.loading_page, null);

        errorPage = LayoutInflater.from(getActivity()).inflate(R.layout.error_page, null);
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);


        ivTitleSetting.setVisibility(View.GONE);
        setListener();
    }

    @Override
    public void setListener() {

    }

    private void MyThreadStart() {
        //在分线程中，实现进度的动态变化
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 85; i++) {
                    try {
                        Thread.sleep(20);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void bannerlistener() {
        String[] titles = new String[]{""+R.string.share, ""+R.string.ren, ""+R.string.xiang, ""+R.string.gou};
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setBannerTitles(Arrays.asList(titles));
        banner.isAutoPlay(true);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imgUrls);
        banner.setImageLoader(new BannerLoader());
        banner.start();

    }
    @Override
    public void onGetDataSucces(BannerData data) {

        Log.i("onGetDataSucces", "onGetDataSucces: "+data.toString());
        imgUrls=new ArrayList<>();
        for (int i = 0; i <data.getImageArr().size() ; i++) {
            imgUrls.add(data.getImageArr().get(i).getIMAURL());
        }
        bannerlistener();
    }

    @Override
    public void onGetDataListSuccess(List<BannerData> data) {

        Log.i("onGetDataListSuccess", "onGetDataListSuccess: "+data.size());
    }
//https://img.zcool.cn/community/014e5058b141baa801219c775045ba.gif
    @Override
    public void onGetDataFailed(String message) {
        //错误页面
       home.addView(errorPage,params);
    }

    @Override
    public void showLoading() {
        home.addView(loadingPage,params);

    }

    @Override
    public void hideLoading() {
        home.removeView(loadingPage);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_home;
    }
}
