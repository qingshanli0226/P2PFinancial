package com.example.p2pdemo.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.modulebase.BaseActivity;
import com.example.modulebase.BaseFragment;
import com.example.modulebase.User;
import com.example.p2pdemo.R;
import com.example.p2pdemo.activity.LoginActivity;

import java.util.Objects;

import butterknife.BindView;

//首页
public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_title_black)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @BindView(R.id.recharge)
    ImageView recharge;
    @BindView(R.id.withdraw)
    ImageView withdraw;
    @BindView(R.id.tv_me_invest)
    TextView tvMeInvest;
    @BindView(R.id.tv_me_intuition)
    TextView tvMeIntuition;
    @BindView(R.id.tv_me_assets)
    TextView tvMeAssets;
    @BindView(R.id.tv_me_name)
    TextView tvMeName;

    @Override
    protected void initTitle() {
        tvTitle.setText(R.string.tab_text3);
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        isLogin();//判断用户是否已经登陆
    }

    private void isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if (TextUtils.isEmpty(name)) {
            doLogin();//本地没有保存 ,用户没用登录
        } else {
            doUser();//已经登陆过,则直接加载用户的信息并显示
        }
    }

    //加载已登录的用户信息
    private void doUser() {

        User user = ((BaseActivity) Objects.requireNonNull(getActivity())).readUser();
        tvMeName.setText(user.getName());
        Glide.with(getContext()).load(user.getImageurl()).into(ivMeIcon);


    }

    private void doLogin() {
        new AlertDialog.Builder(getContext())
                .setTitle("登录")
                .setMessage("您还没用登录,请登录~")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToActivity(LoginActivity.class,null);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void goToActivity(Class clss, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clss);
        //携带数据
        if (bundle != null && bundle.size() != 0)
            intent.putExtra("data", bundle);

        startActivity(intent);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }
}
