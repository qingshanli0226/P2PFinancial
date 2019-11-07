package com.example.p2pfiancial.fragment.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.base.BaseFragment;
import com.example.base.presenter.IBasePresenter;
import com.example.commen.P2PError;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.HomeBannerBean;
import com.example.p2pfiancial.util.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeBannerBean> {

    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;
    private Banner mBanner;
    private IBasePresenter iBasePresenter;
    private List<String> imagePath;
    final int BANNER_REQUEST_CODE = 1001;

    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);

        iBasePresenter.doHttpRequest(BANNER_REQUEST_CODE);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);
        mBanner = view.findViewById(R.id.mBanner);
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText("首页");
        mIvTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, HomeBannerBean data) {
        imagePath = new ArrayList<>();
        for (int i = 0; i < data.getImageArr().size(); i++) {
            imagePath.add(data.getImageArr().get(i).getIMAURL());
        }

        showBanner();
    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<HomeBannerBean> data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {
        UIUtils.toast(error.getErrorMessage(), false);
    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }

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
//                            Log.i("1111111111", "displayImage: " + path);
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        iBasePresenter.detachView();
        Log.i("fragment", "onDestroyView: ");
    }


}
