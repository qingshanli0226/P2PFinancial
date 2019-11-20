package com.example.administrator.p2pdemotext.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Presenter.HomePresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.Util.PageUtil;
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
    PageUtil pageUtil;
//    View inflate;
           private RelativeLayout homeepageReLayout;
//    RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

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
        iBasePresenter.ongetHttp();
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



    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

//    @Override
//    protected int getRvid() {
//        return R.id.homeepageReLayout;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        fragmenthome = (Banner)view.findViewById(R.id.fragmenthome);
        fragmenthomeView = (HomeView) view.findViewById(R.id.fragmenthomeView);
        fragmenthomeButton = (Button) view.findViewById(R.id.fragmenthomeButton);

        pageUtil =new PageUtil(getContext());
        homeepageReLayout = (RelativeLayout) view.findViewById(R.id.homeepageReLayout);
        pageUtil.setReview(homeepageReLayout);
//
//        inflate= LayoutInflater.from(getContext()).inflate(R.layout.loadphoto, null);
//        ImageView imageView=inflate.findViewById(R.id.loadPhotoImageView);
//        Glide.with(getContext()).load(R.mipmap.rongrong_cl).into(imageView);

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
    public void onShow(int code) {
        if (code==200){
            Log.d("SSH",code+"");
            Toast.makeText(getActivity(), "200", Toast.LENGTH_SHORT).show();

            //homeepageReLayout.addView(inflate,params);
            pageUtil.showLoad();
        }else if (code==300){
            Toast.makeText(getActivity(), "300", Toast.LENGTH_SHORT).show();
//            homeepageReLayout.removeView(inflate);
            pageUtil.hideload();
        }
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
