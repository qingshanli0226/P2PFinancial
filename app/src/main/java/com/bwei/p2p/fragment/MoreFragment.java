package com.bwei.p2p.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bwei.base.BaseFragment;
import com.bwei.base.UserManager;
import com.bwei.p2p.GestureActivity;
import com.bwei.p2p.R;
import com.bwei.p2p.RegionActivity;

public class MoreFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageViewLift;
    private ImageView imageViewRight;
    private ToggleButton more;
    private TextView tvRegist;
    private TextView tvReSet;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }
    @Override
    protected void initDate() {
        setTitles();
        setGesture();
    }

    private void setGesture() {
        more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    new AlertDialog.Builder(getContext())
                            .setTitle("提示")
                            .setMessage("是否确定设置手势密码")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    UserManager.getInstance().saveTrueGesture();
                                    getActivity().startActivity(new Intent(getContext(), GestureActivity.class));
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    more.setChecked(false);
                                    dialog.dismiss();
                                }
                            })
                            .show();

                }
            }
        });

        tvReSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (more.isChecked()){
                    UserManager.getInstance().saveTrueGesture();
                    getActivity().startActivity(new Intent(getContext(), GestureActivity.class));
                }else{
                    Toast.makeText(getContext(),"手势密码操作已关闭,请开启后重试",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(),RegionActivity.class));
            }
        });
    }

    private void setTitles() {
        textView.setText("更多");
        imageViewLift.setVisibility(View.INVISIBLE);
        imageViewRight.setVisibility(View.INVISIBLE);

    }
    protected void initView() {
        textView = mView.findViewById(R.id.tv_title);
        imageViewLift= mView.findViewById(R.id.iv_title_back);
        imageViewRight = mView.findViewById(R.id.iv_title_setting);
        more=mView.findViewById(R.id.toggle_more);
        tvRegist=mView.findViewById(R.id.tv_more_regist);
        tvReSet=mView.findViewById(R.id.tv_more_reset);
    }
}
