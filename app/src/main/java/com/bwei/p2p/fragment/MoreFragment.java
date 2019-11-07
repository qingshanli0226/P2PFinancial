package com.bwei.p2p.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.GestureActivity;
import com.bwei.p2p.R;

public class MoreFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageViewLift;
    private ImageView imageViewRight;
    private ToggleButton more;
    private AlertDialog dialog;
    private View viewDialog;
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
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(viewDialog);
                    dialog=builder.create();
                    dialog.show();

                    dialog.getButton(R.id.dialog_Yes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            跳转页面
                            mActivity.startActivity(new Intent(getContext(), GestureActivity.class));
                        }
                    });
                    dialog.getButton(R.id.dialog_No).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                }
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
        viewDialog=mActivity.getLayoutInflater().inflate(R.layout.more_dialog,null);


    }
}
