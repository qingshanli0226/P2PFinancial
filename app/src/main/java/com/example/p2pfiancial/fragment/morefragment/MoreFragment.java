package com.example.p2pfiancial.fragment.morefragment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.commen.P2PError;
import com.example.p2pfiancial.R;

import java.util.List;

public class MoreFragment extends BaseFragment {

    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;
    private TextView mTvMoreRegister;
    private CheckBox mToggleMore;
    private TextView mTvMoreReset;
    private RelativeLayout mRlMoreContact;
    private TextView mTvMorePhone;
    private TextView mTvMoreFankui;
    private TextView mTvMoreShare;
    private TextView mTvMoreAbout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);
        mTvMoreRegister = (TextView) view.findViewById(R.id.tv_more_register);
        mToggleMore = (CheckBox) view.findViewById(R.id.toggle_more);
        mTvMoreReset = (TextView) view.findViewById(R.id.tv_more_reset);
        mRlMoreContact = (RelativeLayout) view.findViewById(R.id.rl_more_contact);
        mTvMorePhone = (TextView) view.findViewById(R.id.tv_more_phone);
        mTvMoreFankui = (TextView) view.findViewById(R.id.tv_more_fankui);
        mTvMoreShare = (TextView) view.findViewById(R.id.tv_more_share);
        mTvMoreAbout = (TextView) view.findViewById(R.id.tv_more_about);
    }

    @Override
    protected void initData() {
        //用户注册
        userResgist();

    }

    private void userResgist() {


    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        //更多
        mTvTitle.setText(R.string.app_fragment_more_title);
        mIvTitleSetting.setVisibility(View.GONE);
    }


    @Override
    public void onHttpRequestDataSuccess(int requestCode, Object data) {

    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {

    }
}
