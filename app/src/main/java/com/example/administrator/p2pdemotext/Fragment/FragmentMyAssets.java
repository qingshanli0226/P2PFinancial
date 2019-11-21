package com.example.administrator.p2pdemotext.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

public class FragmentMyAssets extends BaseFragment<Object> {
    private RelativeLayout rlMe;
    private RelativeLayout rlMeIcon;
    private SimpleDraweeView ivMeIcon;
    private TextView tvMeName;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;

    @Override
    protected void initData() {
        super.initData();
        //圆形图片
       ivMeIcon.setImageURI("http://tu.maomaogougou.cn/picture/2015/12/8ecd86664fb20d69f84f66433098378b.jpg");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myassets;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        rlMe = (RelativeLayout) view.findViewById(R.id.rl_me);
        rlMeIcon = (RelativeLayout) view.findViewById(R.id.rl_me_icon);
        ivMeIcon = (SimpleDraweeView) view.findViewById(R.id.iv_me_icon);
        tvMeName = (TextView) view.findViewById(R.id.tv_me_name);
        recharge = (ImageView) view.findViewById(R.id.recharge);
        withdraw = (ImageView) view.findViewById(R.id.withdraw);
        llTouzi = (TextView) view.findViewById(R.id.ll_touzi);
        llTouziZhiguan = (TextView) view.findViewById(R.id.ll_touzi_zhiguan);
        llZichan = (TextView) view.findViewById(R.id.ll_zichan);

    }



    @Override
    protected void initTopTitle() {

    }
}
