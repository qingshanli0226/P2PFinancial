package com.example.administrator.p2pdemotext.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Presenter.HomePresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.View.HomeView;
import com.example.base.IBasePresenter;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomepage extends BaseFragment<Bean> {
    ArrayList<String> namearr=new ArrayList<>();
    ArrayList<String> arr=new ArrayList<>();
    int i=0;
    //TODO p层的接口
    IBasePresenter iBasePresenter;

    //TODO banner轮播图
    private Banner fragmenthome;

    //轮播图集合
    private ArrayList<String> image_uri=new ArrayList<>();

    Thread thread;
    private HomeView fragmenthomeView;
    boolean flag=false;
    private Button fragmenthomeButton;

    @Override
    protected void initData() {
        super.initData();
        iBasePresenter=new HomePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData();
        //已进入界面让他默认选中第一个
        fragmenthomeView.setProgress(0);
        //进来先把集合清空一下
        arr.clear();
        namearr.clear();
        //自定义view画圆
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (i=0;i<=90;i++){


                    try {
                        fragmenthomeView.setProgress(i);
                        Thread.sleep(20);
                        fragmenthomeView.postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                }
                flag=true;
            }
        };
        thread =new Thread(runnable);
        thread.start();
        if (flag==true){
            thread=null;
        }

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                if (i<=270){
//                    i++;
//                    fragmenthomeView.setProgress(i);
//                    fragmenthomeView.invalidate();
//                }else {
//
//                }
//            }
//        }.start();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        fragmenthome = (Banner)view.findViewById(R.id.fragmenthome);
        fragmenthomeView = (HomeView) view.findViewById(R.id.fragmenthomeView);
        fragmenthomeButton = (Button) view.findViewById(R.id.fragmenthomeButton);

    }
    //获取到的数据
    @Override
    public void onGetDataSucess(Bean data) {
        //获取图片的内容
        List<Bean.ImageArrBean> imageArr = data.getImageArr();
        for (Bean.ImageArrBean imageArrBean : imageArr) {
            String imapaurl = imageArrBean.getIMAURL();
            arr.add(imapaurl);
        }
        //定义图片内的文字
        namearr.add("分享砍学费");
        namearr.add("人脉总动员");
        namearr.add("想不到你是这样的app");
        namearr.add("购物街,爱不单行");

        fragmenthome.setDelayTime(2000);
        //这个是让banner显示文字
        fragmenthome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //banner的加载器
        fragmenthome.setImageLoader(new BannerImageLoader());
        fragmenthome.setImages(arr);
        //banner的动画效果
        fragmenthome.setBannerAnimation(Transformer.CubeOut);
        //放入文字集合
        fragmenthome.setBannerTitles(namearr);

        fragmenthome.start();
    }

    @Override
    protected void initTopTitle() {

    }
    class BannerImageLoader extends ImageLoader{
        @Override
        public ImageView createImageView(Context context) {
            return super.createImageView(context);
        }

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
