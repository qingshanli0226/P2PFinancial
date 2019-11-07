package com.example.p2pdemo.fragment;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.modulebase.BaseFragment;
import com.example.modulebase.IBaseView;
import com.example.modulecommon.RoundProgressView;
import com.example.p2pdemo.R;
import com.example.p2pdemo.presenter.HomePresenter;
import com.youth.banner.Banner;

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

    private HomePresenter presenter;
    private int currentProgress;

    @Override
    protected void initTitle() {
        tbTitle.setText("首页");
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        presenter = new HomePresenter();
        presenter.attachView(this);
    }

    @Override
    protected void loadData() {

       mProgress.setValue(80,homeProgress);
        mProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(100)+1;
                Log.i("LW", "loadData: "+i);
                mProgress.setValue(i,homeProgress);
            }
        });

    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onLoadDataSuccess(int requestCode, Object data) {

    }

    @Override
    public void onLoadDataListSuccess(int requestCode, List<Object> data) {

    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }
}
