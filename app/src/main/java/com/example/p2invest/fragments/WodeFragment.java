package com.example.p2invest.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.p2invest.R;
import com.example.p2invest.view.LoginActivity;

public class WodeFragment extends BaseFragment {

    private TextView tvTitle;
    private ImageView ivTitleSetting;
    private View titleView;
    private LinearLayout titleLine;
    private ImageView ivmeicon;
    private RelativeLayout rlmeicon;
    private TextView tv_me_name;
    private RelativeLayout rlme;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView lltouzi;
    private TextView lltouzizhiguan;
    private TextView llzichan;
    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean b = login.getBoolean("b", false);
        if (b){

        }else {
            new AlertDialog.Builder(this.getActivity())
                    .setTitle("提示")
                    .setMessage("您还没有登录哦！么么~")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            UIUtils.toast("进入登录页面",false);
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        tvTitle.setText(R.string.wode);
    }

    @Override
    public void initView() {
        tvTitle = view.findViewById(R.id.tvtitle);
        ivTitleSetting = view.findViewById(R.id.ivtitlesetting);


        titleView = view.findViewById(R.id.titleView);
        titleLine = view.findViewById(R.id.titleLine);
        ivmeicon = view.findViewById(R.id.ivmeicon);
        rlmeicon = view.findViewById(R.id.rlmeicon);
        tv_me_name = view.findViewById(R.id.tv_me_name);
        rlme = view.findViewById(R.id.rlme);

        recharge = view.findViewById(R.id.recharge);
        withdraw = view.findViewById(R.id.withdraw);
        lltouzi = view.findViewById(R.id.lltouzi);
        lltouzizhiguan = view.findViewById(R.id.lltouzizhiguan);
        llzichan = view.findViewById(R.id.llzichan);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.fragment_wode;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}

