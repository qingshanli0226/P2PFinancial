package com.example.p2pdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.modulebase.BaseActivity;
import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;
import com.example.p2pdemo.activity.MainActivity;
import com.example.p2pdemo.activity.UserRegistActivity;

import butterknife.BindView;

//首页
public class MoreFragment extends BaseFragment {
    @BindView(R.id.iv_title_black)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_more_register)
    TextView tvMoreRegister;
    @BindView(R.id.tv_more_secret)
    TextView tvMoreSecret;
    @BindView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @BindView(R.id.iv_more_switch)
    Switch ivMoreSwitch;
    @BindView(R.id.iv_more_contact)
    TextView ivMoreContact;
    @BindView(R.id.iv_more_sms)
    TextView ivMoreSms;
    @BindView(R.id.iv_more_share)
    TextView ivMoreShare;
    @BindView(R.id.tv_more_about)
    TextView tvMoreAbout;

    private SharedPreferences sp;//sp存储

    @Override
    protected void initTitle() {
        tvTitle.setText(R.string.tab_text4);
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        //初始化sp
        sp = getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);

        //用户注册
        userRegistration();
    }

    private void userRegistration() {
        tvMoreRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(UserRegistActivity.class, null);
            }

            private void goToActivity(Class<UserRegistActivity> userRegistActivityClass, Bundle bundle) {
                Intent intent = new Intent(getActivity(), userRegistActivityClass);
                //携带数据
                if (bundle != null && bundle.size() != 0)
                    intent.putExtra("data", bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_more;
    }

}
