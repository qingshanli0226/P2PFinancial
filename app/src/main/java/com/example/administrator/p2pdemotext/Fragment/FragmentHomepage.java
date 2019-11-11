package com.example.administrator.p2pdemotext.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.View.HomeView;
import com.youth.banner.Banner;

public class FragmentHomepage extends BaseFragment<Object> {
    private Banner fragmenthome;
    private HomeView fragmenthomeView;
    private Button fragmenthomeButton;
    @Override
    protected void initData() {
        super.initData();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmenthomepage;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        fragmenthome = (Banner)view.findViewById(R.id.fragmenthome);
        fragmenthomeView = (HomeView) view.findViewById(R.id.fragmenthomeView);
        fragmenthomeButton = (Button) view.findViewById(R.id.fragmenthomeButton);

    }

    @Override
    protected void initTopTitle() {

    }
}
