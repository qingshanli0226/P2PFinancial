package com.bwei.p2p.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bwei.p2p.R;

public class UserFragment extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_me,container,false);
        setTitles();
        initView();
        return mView;

    }
    private void setTitles() {
        TextView textView = mView.findViewById(R.id.tv_title);
        ImageView iv_l= mView.findViewById(R.id.iv_title_back);
        ImageView iv_r = mView.findViewById(R.id.iv_title_setting);
        textView.setText("我的");
        iv_l.setVisibility(View.INVISIBLE);
        iv_r.setVisibility(View.INVISIBLE);

    }
    private void initView() {

    }
}
