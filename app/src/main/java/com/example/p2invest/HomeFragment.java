package com.example.p2invest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView tv_title;
    private ImageView iv_title_setting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        tv_title=view.findViewById(R.id.tv_title);
        iv_title_setting=view.findViewById(R.id.iv_title_setting);


        iv_title_setting.setVisibility(View.GONE);
        return view;
    }
}
