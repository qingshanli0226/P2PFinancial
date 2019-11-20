package jni.example.p2pinvest.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import jni.example.base.BaseFragment;
import jni.example.p2pinvest.manager.LoginManager;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.mvp.view.activity.HandPwdActivity;
import jni.example.p2pinvest.mvp.view.activity.LoginActivity;

public class MoreFragment extends BaseFragment {

    private TextView tvMoreRegist;
    private ToggleButton toggleMore;
    private TextView tvMoreReset;
    private RelativeLayout rlMoreContact;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;
    private LoginManager loginManager;

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

        loginManager = LoginManager.getLoginManager(getContext());
    }
    @Override
    public void initData() {

        //TODO 判断设置手势密码
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(!loginManager.isLogin()){
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        toggleMore.setChecked(false);
                    }else{
                        toggleMore.setChecked(true);
                        startActivity(new Intent(getContext(),HandPwdActivity.class));
                    }
                }
            }
        });
    }
}
