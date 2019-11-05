package com.bwei.p2p.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bwei.base.IBasePresenter;
import com.bwei.base.IbaseView;
import com.bwei.net.AppNetConfig;
import com.bwei.p2p.BuildConfig;
import com.bwei.p2p.R;
import com.bwei.p2p.RoundProgress;
import com.bwei.p2p.bean.Image;
import com.bwei.p2p.bean.Index;
import com.bwei.p2p.present.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IbaseView<Index> {
    private View mView;
    private IBasePresenter iBasePresenter;
    private RoundProgress roundProHome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_home,container,false);
        setTitles();
        initView();

        return mView;

    }

    private void setTitles() {
        TextView textView = mView.findViewById(R.id.tv_title);
        ImageView iv_l= mView.findViewById(R.id.iv_title_back);
        ImageView iv_r = mView.findViewById(R.id.iv_title_setting);
        textView.setText("首页");
        iv_l.setVisibility(View.INVISIBLE);
        iv_r.setVisibility(View.INVISIBLE);

    }

    private List<Image> imgList;
    private List<String> imgtitleList;
    private void initView() {

        imgList=new ArrayList<>();
        imgtitleList=new ArrayList<>();
//        Banner banner = mView.findViewById(R.id.banner);
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        banner.setImageLoader(new ImageLoader() {
//                    @Override
//                    public void displayImage(Context context, Object path, ImageView imageView) {
//                        Glide.with(context).load(path).into(imageView);
//                    }
//                });
//        for (int i = 0; i < 4; i++) {
//            imgList.add(R.mipmap.ic_launcher);
//        }
        imgtitleList.add("banner1");
        imgtitleList.add("banner2");
        imgtitleList.add("banner3");
        imgtitleList.add("banner4");
        roundProHome=mView.findViewById(R.id.roundProHome);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <  85; i++) {
//                    roundProHome.setProgress(i + 1);
//
//                    SystemClock.sleep(20);
//                    //强制重绘
////                roundProHome.invalidate();//只有主线程才可以如此调用
//                    roundProHome.postInvalidate();//主线程、分线程都可以如此调用
//                }
//            }
//        }).start();
//        banner.setImages(imgList)
//                .isAutoPlay(true)
//                .setDelayTime(1000)
//                .setBannerTitles(imgtitleList)
//                .start();



    }

    @Override
    public void onGetDataSucess(Index data) {
        Log.i("ssss", "获取数据成功onGetDataSucess: "+data.product);
        Toast.makeText(getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
        imgList.addAll(data.images);
//        imgtitleList.add(data.product.name);
        iBasePresenter=new HomePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getDate(AppNetConfig.INDEX,null);
        Banner banner = mView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(imgList)
                .isAutoPlay(true)
                .setDelayTime(1000)
                .setBannerTitles(imgtitleList)
                .start();


    }

    @Override
    public void onGetDataListSucess(List<Index> data) {

    }

    @Override
    public void onGetDataFailed(String message) {
        Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
