package com.bw.jinrong.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import cn.sharesdk.onekeyshare.OnekeyShare;
import com.bw.base.BaseFragment;
import com.bw.common.UIUtils;
import com.bw.jinrong.R;
import com.bw.jinrong.activity.RegisterActivity;
import com.bw.jinrong.activity.ShowActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment {

    private View view;

    @Override
    protected void initView() {
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("更多");
    }

    @Override
    protected void initData() {
        view = getBaseView();

        TextView tv_more_regist = view.findViewById(R.id.tv_more_regist);
        tv_more_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

        ToggleButton toggle_more = view.findViewById(R.id.toggle_more);
        toggle_more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    startActivity(new Intent(getContext(), ShowActivity.class));
                }
            }
        });

        TextView tv_more_share = view.findViewById(R.id.tv_more_share);
        tv_more_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.share));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(UIUtils.getContext());
    }

    @Override
    protected int setView() {
        return R.layout.fragment_more;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
