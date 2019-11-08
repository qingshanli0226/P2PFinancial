package com.bwei.p2p.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bwei.base.BaseFragment;
import com.bwei.base.IBasePresenter;
import com.bwei.base.IbaseView;
import com.bwei.base.bean.Index;
import com.bwei.p2p.R;
import com.bwei.p2p.RoundProgress;
import com.bwei.p2p.presenter.HomePresenter;
import com.bwei.p2p.util.LoadingAinm;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements IbaseView<Index> {
    private IBasePresenter iBasePresenter;
    private RoundProgress roundProHome;
    private TextView textView;
    private TextView tvHomeProduct;
    private ImageView imageViewLift;
    private ImageView imageViewRight;
    private Banner banner;
    private List<String> imgList;
    private AnimationDrawable anim;
    private List<String> imgtitleList;
    private View dialogView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                roundProHome.setProgress();
            }
        }
    };

    protected void initView() {
        banner = mView.findViewById(R.id.banner);
        textView = mView.findViewById(R.id.tv_title);
        imageViewLift = mView.findViewById(R.id.iv_title_back);
        imageViewRight = mView.findViewById(R.id.iv_title_setting);
        roundProHome = mView.findViewById(R.id.roundProHome);
        tvHomeProduct = mView.findViewById(R.id.tv_home_product);
        dialogView=mActivity.getLayoutInflater().inflate(R.layout.dialog,null);
        iBasePresenter = new HomePresenter();

    }
    @Override
    protected void initDate() {
        imgList = new ArrayList<>();
        imgtitleList = new ArrayList<>();
        iBasePresenter.attachView(this);
        iBasePresenter.getDate();
        setTitles();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<85;i++){
                    try {
                        Thread.sleep(200);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException ignored) {
                    }
                }

            }
        }).start();
    }


    private void setTitles() {
        textView.setText("首页");
        imageViewLift.setVisibility(View.INVISIBLE);
        imageViewRight.setVisibility(View.INVISIBLE);

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onGetDataSucess(Index data) {
        for (int i=0;i<data.imageArr.size();i++) {
            imgList.add(data.imageArr.get(i).IMAURL);
        }
        tvHomeProduct.setText(data.proInfo.name);
            Toast.makeText(getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
            initBanner();


    }

    @Override
    public void onGetDataListSucess(List<Index> data) {
    }

    private void initBanner() {
        imgtitleList.add("banner1");
        imgtitleList.add("banner2");
        imgtitleList.add("banner3");
        imgtitleList.add("banner4");
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
    public void showLoading() {
//        加载页面
        LoadingAinm.showLodingView(mView);

    }

    @Override
    public void hideLoading(int i) {
//        隐藏页面
        LoadingAinm.hideView(mView,i);
        if (i==1){
            ImageView img = mView.findViewById(R.id.dialog_gif);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iBasePresenter.getDate();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
