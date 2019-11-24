package com.bwei.p2p.user;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwei.base.BaseFragment;
import com.bwei.base.UserManager;
import com.bwei.p2p.R;
import com.wyp.avatarstudio.AvatarStudio;

public class UserFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageViewLift;
    private ImageView imageIcon;
    private ImageView imageViewRight;
    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initDate() {
        setTitles();
//        判断弹出对话框
        String s = UserManager.getInstance().readIcon();
        if (!("无".equals(s))){
            Glide.with(getContext()).load(s).apply(new RequestOptions().circleCrop()).into(imageIcon);
        }
        imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AvatarStudio.Builder(getActivity()).needCrop(false)
                        .setOutput(60,60)
                        .setTextColor(Color.BLUE)
                        .setText("相机","相册","取消")
                        .show(new AvatarStudio.CallBack() {
                            @Override
                            public void callback(String uri) {
                                UserManager.getInstance().saveIcon(uri);
                                Glide.with(getContext()).load(uri).apply(new RequestOptions().circleCrop()).into(imageIcon);
                            }
                        });
            }
        });
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
        imageIcon = mView.findViewById(R.id.iv_me_icon);

    }
}
