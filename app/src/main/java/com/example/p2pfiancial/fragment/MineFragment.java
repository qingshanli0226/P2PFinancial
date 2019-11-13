package com.example.p2pfiancial.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;

public class MineFragment extends BaseFragment {
    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;
    private RelativeLayout mRlMe;
    private RelativeLayout mRlMeIcon;
    private ImageView mIvMeIcon;
    private TextView mTvMeName;
    private ImageView mRecharge;
    private ImageView mWithdraw;
    private TextView mLlTouzi;
    private TextView mLlTouziZhiguan;
    private TextView mLlZichan;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);
        mRlMe = (RelativeLayout) view.findViewById(R.id.rl_me);
        mRlMeIcon = (RelativeLayout) view.findViewById(R.id.rl_me_icon);
        mIvMeIcon = (ImageView) view.findViewById(R.id.iv_me_icon);
        mTvMeName = (TextView) view.findViewById(R.id.tv_me_name);
        mRecharge = (ImageView) view.findViewById(R.id.recharge);
        mWithdraw = (ImageView) view.findViewById(R.id.withdraw);
        mLlTouzi = (TextView) view.findViewById(R.id.ll_touzi);
        mLlTouziZhiguan = (TextView) view.findViewById(R.id.ll_touzi_zhiguan);
        mLlZichan = (TextView) view.findViewById(R.id.ll_zichan);
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        //我的资产
        mTvTitle.setText(getResources().getString(R.string.app_fragment_mine_top_title));
    }


}
