package com.example.p2pfiancial.fragment.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.activity.info.UserInfoActivity;
import com.example.p2pfiancial.activity.login.UserLoinActivity;
import com.example.p2pfiancial.bean.LoginBean;
import com.example.p2pfiancial.userinfo.UserInfoManager;
import com.example.p2pfiancial.util.UIUtils;

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

        //判断登录状态63
        if (UserInfoManager.getInstance().isLogin()) {
            doUser();
        }
    }

    /**
     * 读取用户信息进行展示
     */
    private void doUser() {
        //1.读取本地保存的用户信息
        LoginBean.DataBean dataBean = UserInfoManager.getInstance().readUserInfo();

        //2.获取对象信息，并设置给相应的视图显示。
        mTvMeName.setText(dataBean.getName());
        String imageUrl = dataBean.getImageurl();
        //glide设置头像
        Glide.with(this).load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())) //设置圆形头像
                .skipMemoryCache(true) //设置内存缓存开关
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置磁盘缓存模式 all: 所有图片
                .into(mIvMeIcon);
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        //我的资产
        mTvTitle.setText(getResources().getString(R.string.app_fragment_mine_top_title));
        mIvTitleSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserInfoManager.getInstance().isLogin()) {
                    Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                    startActivity(intent);
                } else {
                    UIUtils.toast("您还未登录, 请登录", false);
                    Intent intent = new Intent(getActivity(), UserLoinActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
