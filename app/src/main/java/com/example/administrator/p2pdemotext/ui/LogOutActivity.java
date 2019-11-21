package com.example.administrator.p2pdemotext.ui;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.immersionbar.ImmersionBar;
import com.squareup.picasso.Picasso;
import com.wyp.avatarstudio.AvatarStudio;

import java.io.File;
import java.util.List;

public class LogOutActivity extends BaseActivity {
    private TextView activityLogOutFinsh;
    private SimpleDraweeView activityLogOutFacebook;
    private Button activityLogOutChangePhoto;
    private Button activityLogOutExit;




    @Override
    protected void initData() {
        //沉浸式布局
        ImmersionBar.with(LogOutActivity.this).init();

        //退出
        activityLogOutFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //更改头像
        activityLogOutChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AvatarStudio.Builder(LogOutActivity.this)
                        .dimEnabled(true)
                        .needCrop(true)
                        .setTextColor(Color.GREEN)
                        .setText("拍照","图库选择","取消")
                        .setAspect(1,1)
                        .setOutput(150,150)
                        .show(new AvatarStudio.CallBack() {
                            @Override
                            public void callback(String uri) {
                                activityLogOutFacebook.setImageURI(Uri.fromFile(new File(uri)));
                            }
                        });
            }
        });

    }

    @Override
    protected void initView() {
        activityLogOutFinsh = (TextView) findViewById(R.id.activityLogOutFinsh);
        activityLogOutExit = (Button) findViewById(R.id.activityLogOutExit);
        activityLogOutFacebook = (SimpleDraweeView) findViewById(R.id.activityLogOutFacebook);
        activityLogOutChangePhoto = (Button) findViewById(R.id.activityLogOutChangePhoto);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_log_out;
    }

    @Override
    public void onGetDataSucess(Object data) {

    }

    @Override
    public void onGetDataListSucess(List data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
