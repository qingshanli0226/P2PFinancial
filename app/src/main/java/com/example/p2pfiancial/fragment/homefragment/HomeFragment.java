package com.example.p2pfiancial.fragment.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.base.BaseFragment;
import com.example.base.presenter.IBasePresenter;
import com.example.commen.P2PError;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.HomeBannerBean;
import com.example.p2pfiancial.cache.CacheManager;
import com.example.p2pfiancial.common.RoundProgress;
import com.example.p2pfiancial.util.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment<HomeBannerBean> implements CacheManager.IHomeReceivedListener {

    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;
    private Banner mBanner;
    private IBasePresenter iBasePresenter;
    private List<String> imagePath;
    final int BANNER_REQUEST_CODE = 1001;

    private View view;
    private RoundProgress mRundProgress;
    private HomeBannerBean data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册数据监听
        CacheManager.getInstance().registerIHomeReceivedListener(this);
    }

    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        this.view = view;
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);
        mBanner = view.findViewById(R.id.mBanner);
        mRundProgress = view.findViewById(R.id.mRp_roundProgress);

    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText(getResources().getString(R.string.app_fragment_home_top_title));
        mIvTitleSetting.setVisibility(View.GONE);
    }


    @Override
    protected void initData() {
        this.data = CacheManager.getInstance().getHomeData();
        Log.i("TAG", "initData: " + data);
        setBanner();


        if (!isConnected()) {
            UIUtils.toast("当前网络没有连接", false);
            return;
        }
        UIUtils.toast("当前网络连接正常", false);

//        iBasePresenter = new HomePresenter();
//        iBasePresenter.attachView(this);
//        iBasePresenter.doHttpRequest(BANNER_REQUEST_CODE);

        // 设置小圆圈
        setRoundProgress();
    }

    private void setRoundProgress() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mRundProgress.setMax(360);
                currentProgress++;
                if (currentProgress <= 360 * 0.9) {
                    mRundProgress.setProgress(currentProgress);
                    mRundProgress.postInvalidate();
                } else {
                    this.cancel();
                }
            }
        }, 0, 20);
    }

    //接口回调数据
    @Override
    public void onHomeDataReceived(HomeBannerBean homeBannerBean) {
        this.data = homeBannerBean;
        Log.i("TAG", "initData2: " + data.getImageArr().size());
        setBanner();
    }

    private void setBanner() {
        imagePath = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < this.data.getImageArr().size(); i++) {
                imagePath.add(data.getImageArr().get(i).getIMAURL());
            }
            showBanner();
        }
    }

    private int currentProgress = 0;




//    //请求的数据
//    @Override
//    public void onHttpRequestDataSuccess(int requestCode, HomeBannerBean data) {
//        if (requestCode == BANNER_REQUEST_CODE) {
//            imagePath = new ArrayList<>();
//            for (int i = 0; i < data.getImageArr().size(); i++) {
//                imagePath.add(data.getImageArr().get(i).getIMAURL());
//            }
//
//            showBanner();
//        }
//    }

    //设置Banner
    private void showBanner() {
        //网络banner图片
        if (imagePath != null) {
            //设置标题集合
            String[] titles = new String[]{getString(R.string.app_fragment_home_banner_title01), getString(R.string.app_fragment_home_banner_title02), getString(R.string.app_fragment_home_banner_title03), getString(R.string.app_fragment_home_banner_title04)};
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    .setImages(imagePath)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(getActivity()).load((String) path).into(imageView);
                        }
                    })
                    .setBannerTitles(Arrays.asList(titles))
                    .isAutoPlay(true)
                    .setDelayTime(1500)
                    .setIndicatorGravity(BannerConfig.CENTER)
                    .start();
        }
    }

    //数据请求失败
    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {
        UIUtils.toast(error.getErrorMessage(), false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        iBasePresenter.detachView();
    }

    /**
     * 网络连接状态监听
     */
    @Override
    public void onConnected() {
        super.onConnected();
        initData();
    }

    @Override
    public void onDisConnected() {
        super.onDisConnected();
        UIUtils.toast("当前网络没有连接", false);
    }
}
