package com.example.administrator.p2pdemotext.ui.SecondaryPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.R;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;

public class AboutUsActivity extends BaseActivity<Object> {
    private Button activityButton;
    private TextView homeActivityTittleBarId;

    @Override
    protected void initData() {
        ImmersionBar.with(this).init();
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        activityButton = (Button) findViewById(R.id.activityButton);
        homeActivityTittleBarId = (TextView) findViewById(R.id.homeActivityTittleBarId);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void onGetDataSucess(Object data) {

    }

    @Override
    public void onGetDataListSucess(List<Object> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
