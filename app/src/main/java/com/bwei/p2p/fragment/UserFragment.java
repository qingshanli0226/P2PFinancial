package com.bwei.p2p.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.LoginActivity;
import com.bwei.p2p.R;

public class UserFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageViewLift;
    private ImageView imageViewRight;
    private View userDialog;
    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initDate() {
        setTitles();
//        判断弹出对话框
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if(TextUtils.isEmpty(name)){
            addDialog();
        }else{
            Toast.makeText(getContext(),"已经登录过",Toast.LENGTH_SHORT).show();
        }

    }

    private void addDialog() {
        new AlertDialog.Builder(getContext()).setTitle("提示").setMessage("亲~您还没有登录哦！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));

                    }
                })
                .setCancelable(false)
                .show();
    }

    private void setTitles() {
        textView.setText("我的");
        imageViewLift.setVisibility(View.INVISIBLE);
        imageViewRight.setVisibility(View.INVISIBLE);

    }
    protected void initView() {
         textView = mView.findViewById(R.id.tv_title);
        imageViewLift= mView.findViewById(R.id.iv_title_back);
        imageViewRight = mView.findViewById(R.id.iv_title_setting);
    }
}
