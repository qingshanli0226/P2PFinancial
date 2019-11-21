package com.example.p2pdemo.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modulebase.BaseFragment;
import com.example.modulecommon.Constructor;
import com.example.p2pdemo.R;
import com.example.p2pdemo.gesture.GestureActivity;
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
        sp = getActivity().getSharedPreferences(Constructor.SP_NAME_GESTURE, Context.MODE_PRIVATE);
        boolean checked = sp.getBoolean(Constructor.KEY_GESTURE_ISOPEN, false);
        ivMoreSwitch.setChecked(checked);
        //用户注册
        userRegistration();
        //手势密码
        gesturePass();
        //重置手势密码
        gestureReset();
    }

    private void gestureReset() {
        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cheked = ivMoreSwitch.isChecked();
                if (cheked) {
                    Bundle bundle = new Bundle();
                    bundle.putString("gesture", "reset");
                    goToActivity(GestureActivity.class, bundle);
                } else
                    Toast.makeText(getContext(), "手势密码未开启,请开启后再设置", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gesturePass() {
        ivMoreSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String inputCode = sp.getString(Constructor.KEY_GESTURE_PWD, "");
                    if (TextUtils.isEmpty(inputCode)) {//之前没有设置过
                        new AlertDialog.Builder(MoreFragment.this.getActivity())
                                .setTitle("设置手势密码")
                                .setMessage("是否现在设置手势密码")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sp.edit().putBoolean(Constructor.KEY_GESTURE_ISOPEN, true).apply();
                                        ivMoreSwitch.setChecked(true);
                                        //开启新的activity:
                                        Bundle bundle = new Bundle();
                                        bundle.putString("gesture", "setting");
                                        goToActivity(GestureActivity.class, bundle);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sp.edit().putBoolean(Constructor.KEY_GESTURE_ISOPEN, false).apply();
                                        ivMoreSwitch.setChecked(false);
                                    }
                                }).show();

                    } else {
                        sp.edit().putBoolean(Constructor.KEY_GESTURE_ISOPEN, true).apply();
                    }
                } else {
                    sp.edit().putBoolean(Constructor.KEY_GESTURE_ISOPEN, false).apply();
                }


            }
        });
    }

    private void userRegistration() {
        tvMoreRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(UserRegistActivity.class, null);
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
