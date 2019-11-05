package com.bwei.p2p.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bwei.base.BaseFragment;
import com.bwei.base.IBasePresenter;
import com.bwei.base.IbaseView;
import com.bwei.net.AppNetConfig;
import com.bwei.p2p.R;
import com.bwei.p2p.RoundProgress;
import com.bwei.p2p.bean.Image;
import com.bwei.p2p.bean.Index;
import com.bwei.p2p.present.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements IbaseView<Image> {
    private IBasePresenter iBasePresenter;
    private RoundProgress roundProHome;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                roundProHome.setProgress();
            }
        }
    };
    private TextView textView;
    private ImageView iv_l;
    private ImageView iv_r;
    private Banner banner;

    @Nullable

    @Override
    protected void initDate() {
        imgList = new ArrayList<>();
        imgtitleList = new ArrayList<>();
        imgtitleList.add("banner1");
        imgtitleList.add("banner2");
        imgtitleList.add("banner3");
        imgtitleList.add("banner4");
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getDate(AppNetConfig.INDEX, null);
        setTitles();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<85;i++){
                    try {
                        Thread.sleep(200);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {

                    }
                }

            }
        }).start();
    }

    protected void initView() {
        banner = mView.findViewById(R.id.banner);
        textView = mView.findViewById(R.id.tv_title);
        iv_l = mView.findViewById(R.id.iv_title_back);
        iv_r = mView.findViewById(R.id.iv_title_setting);
        roundProHome = mView.findViewById(R.id.roundProHome);

    }

    private void setTitles() {
        textView.setText("首页");
        iv_l.setVisibility(View.INVISIBLE);
        iv_r.setVisibility(View.INVISIBLE);

    }

    private List<String> imgList;
    private List<String> imgtitleList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onGetDataSucess(Image data) {



    }

    @Override
    public void onGetDataListSucess(List<Image> data) {
        for (int i = 0; i < data.size(); i++) {
            imgList.add(data.get(i).IMAURL);
        }
        Log.i("ssss", "获取数据成功onGetDataSucess: "+data.size());
        Toast.makeText(getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
        initBanner();
    }

    private void initBanner() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(imgList)
                .isAutoPlay(true)
                .setDelayTime(1000)
                .setBannerTitles(imgtitleList)
                .start();
    }

    @Override
    public void onGetDataFailed(String message) {
        Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
