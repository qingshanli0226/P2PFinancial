package com.example.p2invest.fragments;

import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
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
    private TextView tvtitle;
    private ImageView ivtitlesetting;
   private Banner banner;
    View inflate;
    private ScrollView scrollView;
   MyProgress myProgress;
    RelativeLayout.LayoutParams params;
   private     AlertDialog.Builder dialog;
   private RelativeLayout home;
    ArrayList<String> imgurls;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                myProgress.setProgress();
            }
        }
    };

    IBsePresenter iBsePresenter;

    public void initData() {
        iBsePresenter = new HomePresenter();
        iBsePresenter.attachView(this);
        iBsePresenter.getData(AppNetConfig.INDEX);


        threadstart();
        Log.i("wzy", "initData");

        ivtitlesetting.setVisibility(View.GONE);
    }

    @Override
    public void initData(View view) {
        myProgress=view.findViewById(R.id.my);
        tvtitle=view.findViewById(R.id.tvtitle);
        home=view.findViewById(R.id.homeline);
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.loadingpic, null);
//        ImageView id = inflate.findViewById(R.id.img);
//        Glide.with(getActivity()).load("tps://img.zcool.cn/community/014e5058b141baa801219c775045ba.gif").into(id);

        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        ivtitlesetting=view.findViewById(R.id.ivtitlesetting);
        banner=view.findViewById(R.id.banner);
        scrollView=view.findViewById(R.id.scroll);
        ivtitlesetting.setVisibility(View.GONE);
        setListener();
        initData();
    }

    public void setListener() {

    }

    private void threadstart() {
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
        String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setBannerTitles(Arrays.asList(titles));
        banner.isAutoPlay(true);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imgurls);
        banner.setImageLoader(new BannerLoader());
        banner.start();

    }
    @Override
    public void onGetDataSucces(BannerData data) {
   //     home.removeView(inflate);
        Log.i("onGetDataSucces", "onGetDataSucces: "+data.toString());
        imgurls=new ArrayList<>();
        for (int i = 0; i <data.getImageArr().size() ; i++) {
            imgurls.add(data.getImageArr().get(i).getIMAURL());
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
        Log.i("onGetDataListSuccess", "onGetDataListSuccess: ");
        //http://www.leawo.cn/attachment/201501/29/138176_1422517408VD82.gif
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        home.addView(inflate,params);

    }

    @Override
    public void hideLoading() {
        home.removeView(inflate);
    }

    @Override
    public int layoutId() {
        return R.layout.home;
    }
}
