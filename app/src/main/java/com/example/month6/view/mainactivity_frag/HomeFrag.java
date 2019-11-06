package com.example.month6.view.mainactivity_frag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.month6.R;
import com.example.month6.view.diyview.ProGrossView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFrag extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.proGrossView)
    ProGrossView proGrossView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            proGrossView.reFush();
        }
    };

    public HomeFrag(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected void initData() {
        //更新进度到90%
        updateProgress(0.9);
        setBanner();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_frag;
    }

    //进度条滚动线程,自动结束
    private void updateProgress(final double num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (!(proGrossView.num >= (360 * num)));
            }
        }).start();
    }

    //设置轮播图
    private void setBanner() {
        ArrayList<Bitmap> list = new ArrayList<>();
        list.add(BitmapFactory.decodeResource(fragmentContext.getResources(), R.mipmap.i1));
        list.add(BitmapFactory.decodeResource(fragmentContext.getResources(), R.mipmap.i2));
        list.add(BitmapFactory.decodeResource(fragmentContext.getResources(), R.mipmap.i3));
        list.add(BitmapFactory.decodeResource(fragmentContext.getResources(), R.mipmap.i4));
        ArrayList<String> titles = new ArrayList<>();
        titles.add("标题1");
        titles.add("标题2");
        titles.add("标题3");
        titles.add("标题4");
        banner.setImages(list)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        imageView.setImageBitmap((Bitmap) path);
                    }
                })
                .setBannerAnimation(Transformer.DepthPage)  //动画效果
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)//样式
                .setBannerTitles(titles)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }
}
