package com.example.p2pfiancial.fragment.morefragment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.activity.regist.UserRegistActivity;
import com.example.p2pfiancial.bean.LoginBean;
import com.example.p2pfiancial.gesturelock.GesturesLockActivity;
import com.example.p2pfiancial.userinfo.UserInfoManager;

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
    private LinearLayout mLlLoginShow;

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
        mLlLoginShow = (LinearLayout) view.findViewById(R.id.ll_loginShow);
    }

    @Override
    protected void initData() {
        //用户注册
        mTvMoreRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userResgist();
            }
        });


        //判断登录状态63
        if (UserInfoManager.getInstance().isLogin()){
            mLlLoginShow.setVisibility(View.VISIBLE); //显示
        }else {
            mLlLoginShow.setVisibility(View.GONE); //隐藏
        }

        //登录监听
        UserInfoManager.getInstance().registerUserInfoStatusListener(new UserInfoManager.UserInfoStatusListener() {

            @Override
            public void onUserStatus(boolean isLogin, LoginBean.DataBean userInfo, boolean isPattern, String readGestureLock) {
                if (isLogin){
                    mLlLoginShow.setVisibility(View.VISIBLE); //显示
                }else {
                    mLlLoginShow.setVisibility(View.GONE); //隐藏
                }
            }
        });

        //手势密码
        mToggleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToggleMore.isChecked()) {
                    GesturesLockActivity.Companion.startAction(getActivity());
                } else { //没有被选中, 取消手势密码

                }
            }
        });


    }

    private void etGestureLock() {

    }

    private void userResgist() {
        UserRegistActivity.Companion.startAction(getActivity());
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        //更多
        mTvTitle.setText(R.string.app_fragment_more_title);
        mIvTitleSetting.setVisibility(View.GONE);
    }
}
