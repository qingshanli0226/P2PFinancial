package jni.example.p2pinvest.mvp.view.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import jni.example.base.BaseFragment;
import jni.example.p2pinvest.R;

public class MoreFragment extends BaseFragment {

    private TextView tvMoreRegist;
    private ToggleButton toggleMore;
    private TextView tvMoreReset;
    private RelativeLayout rlMoreContact;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;

    @Override
    public int layoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void init(View view) {
        tvMoreRegist = (TextView) view.findViewById(R.id.tv_more_regist);
        toggleMore = (ToggleButton) view.findViewById(R.id.toggle_more);
        tvMoreReset = (TextView) view.findViewById(R.id.tv_more_reset);
        rlMoreContact = (RelativeLayout) view.findViewById(R.id.rl_more_contact);
        tvMorePhone = (TextView) view.findViewById(R.id.tv_more_phone);
        tvMoreFankui = (TextView) view.findViewById(R.id.tv_more_fankui);
        tvMoreShare = (TextView) view.findViewById(R.id.tv_more_share);
        tvMoreAbout = (TextView) view.findViewById(R.id.tv_more_about);
    }
    @Override
    public void initData() {

    }
}
