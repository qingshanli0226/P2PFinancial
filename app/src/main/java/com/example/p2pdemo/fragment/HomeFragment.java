package com.example.p2pdemo.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.modulebase.BaseFragment;
import com.example.modulebase.IBasePresenter;
import com.example.modulebase.IBaseView;
import com.example.modulecommon.AppNetConfig;
import com.example.modulecommon.RoundProgressView;
import com.example.p2pdemo.MyImageLoader;
import com.example.p2pdemo.R;
import com.example.p2pdemo.bean.HomeBean;
import com.example.p2pdemo.presenter.HomePresenter;
import com.wang.avi.AVLoadingIndicatorView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;


//首页
public class HomeFragment extends BaseFragment implements IBaseView<Object> {
    @BindView(R.id.iv_title_black)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_title)
    TextView tbTitle;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @BindView(R.id.mRoundProgress)
    RoundProgressView mProgress;
    @BindView(R.id.home_progress)
    TextView homeProgress;
    @BindView(R.id.home_loading)
    AVLoadingIndicatorView homeLoading;
    @BindView(R.id.home_yearRate)
    TextView homeYearRate;

    private IBasePresenter iHomePresenter;//声明一个presenter接口
    private List<String> bannerDatas = new ArrayList<>();//banner的数据

    @Override
    protected void initTitle() {
        tbTitle.setText("首页");
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        iHomePresenter = new HomePresenter();
        iHomePresenter.attachView(this);
        iHomePresenter.doHttpRequest(AppNetConfig.HOME_REQUEST_CODE);
    }

    @Override
    protected void loadData() {

        mProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(100) + 1;
                Log.i("LW", "loadData: " + i);
                mProgress.setValue(i, homeProgress);
            }
        });

    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onLoadDataSuccess(int requestCode, Object data) {
        //判断自己所需的数据码
        if (requestCode == AppNetConfig.HOME_REQUEST_CODE) {
            //设置Banner
            HomeBean homeBean = (HomeBean) data;
            List<HomeBean.ImageArrBean> imageArr = homeBean.getImageArr();
            for (int i = 0; i < imageArr.size() - 1; i++) {
                bannerDatas.add(imageArr.get(i).getIMAURL());
            }
            banner.setImages(bannerDatas);
            banner.setBannerAnimation(Transformer.ZoomIn);
            //标题集合
            ArrayList<String> titles = new ArrayList<>();
            titles.add("分享砍学费");
            titles.add("人脉总动员");
            titles.add("想不到你是这样的app");
            titles.add("购物节,爱不单行");
            banner.setBannerTitles(titles);
//            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            banner.isAutoPlay(true);
            banner.setDelayTime(2000);
            banner.setImageLoader(new MyImageLoader());
            banner.start();
            //设置年利率
            homeYearRate.setText(((HomeBean) data).getProInfo().getYearRate())   ;
            //设置标题
           tvHomeProduct.setText(((HomeBean) data).getProInfo().getName());
           //设置进度

            String progress = ((HomeBean) data).getProInfo().getProgress();

            Log.i("TAG", "onLoadDataSuccess: "+progress);

            mProgress.setValue(90, homeProgress);

        }
    }
    @Override
    public void onLoadDataListSuccess(int requestCode, List<Object> data) {

    }

    @Override
    public void showLoading(int requestCode) {
        homeLoading.show();
    }

    @Override
    public void hideLoading(int requestCode) {
        homeLoading.hide();
    }
}
