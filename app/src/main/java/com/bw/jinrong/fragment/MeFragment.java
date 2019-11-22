package com.bw.jinrong.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.bw.base.BaseActivity;
import com.bw.base.BaseFragment;
import com.bw.base.utils.SpUtils;
import com.bw.jinrong.R;
import com.bw.jinrong.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    private View view;

    @Override
    protected void initView() {
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("我的资产");
    }

    @Override
    protected void initData() {

        view = getBaseView();

        //判断用户是否已经登录
        isLogin();
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        String name = SpUtils.getInstance().getString("name");
        Boolean isCheck = SpUtils.getInstance().getBoolean("isCheck");
//        if (TextUtils.isEmpty(name)){
        if (!isCheck){
            //本地没有保存过用户信息，给出提示:登录
            doLogin();
        }else {
            //已经登录过，则直接加载用户的信息并显示
            doUser();
        }
    }

    //加载用户信息并显示
    private void doUser() {
        //读取本地保存的用户信息
//        User user = ((BaseActivity)this.getActivity()).readUser();
        SpUtils.getInstance().getString("");
    }

    //给出提示：登录
    private void doLogin() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("您还没有登录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getContext(),LoginActivity.class));
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    protected int setView() {
        return R.layout.fragment_me;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
