package com.example.p2invest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p2invest.custor.MyProgress;
import com.example.p2invest.R;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    private TextView tv_title;
    private ImageView iv_title_setting;
   private Banner banner;
   private ScrollView scrollView;
   MyProgress myProgress;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                myProgress.setProgress();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        myProgress=view.findViewById(R.id.my);
        tv_title=view.findViewById(R.id.tv_title);
        iv_title_setting=view.findViewById(R.id.iv_title_setting);
        banner=view.findViewById(R.id.banner);
        scrollView=view.findViewById(R.id.scroll);
        iv_title_setting.setVisibility(View.GONE);
        initData();
        setListener();
        return view;
    }

    public void initData() {
        bannerlistener();
        threadstart();
        Log.i("wzy", "initData");

        iv_title_setting.setVisibility(View.GONE);
    }


    public void setListener() {

    }

    private void threadstart() {
        //在分线程中，实现进度的动态变化
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 85; i++) {
                    try {
                        Thread.sleep(20);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void bannerlistener() {

        ArrayList<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);




        String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};

      //  NetInterence interence = RetrofitCreator.getNetInterence();
//       interence.getData(getHearerParmas(), "", getParmas())
//               .subscribeOn(Schedulers.io())
//               .observeOn(Schedulers.io())
//               .subscribe(new Observer<ResponseBody>() {
//                   @Override
//                   public void onSubscribe(Disposable d) {
//                   }
//
//                   @Override
//                   public void onNext(ResponseBody responseBody) {
//                       int i = 0;
//                       while (i <= 100) {
//                           iBaseView.onGetDataFailed("test mem leak");
//                           try {
//                               Thread.sleep(100);
//                           } catch (InterruptedException e) {
//                               e.printStackTrace();
//                           }
//                           i++;
//                       }
//
//                       try {
//                           //如果返回的数据是列表
//                           if (isList()) {
//                               ResEntity<List<T>> resEntityList = new Gson().fromJson(responseBody.string(), getBeanType());
//                               if (resEntityList.getRet().equals("1")) {
//                                   //获取列表数据成功
//                                   if (iBaseView!= null) {
//                                       iBaseView.onGetDataListSuccess(resEntityList.getData());
//                                   }
//                               } else {
//                                   //获取数据失败
//                                   if (iBaseView!= null) {
//                                       iBaseView.onGetDataFailed("获取数据失败");
//                                   }
//                               }
//                           } else {
//                               ResEntity<T> resEntity = new Gson().fromJson(responseBody.string(), getBeanType());
//                               if (resEntity.getRet().equals("1")) {
//                                   //获取数据成功
//                                   if (iBaseView!= null) {
//                                       iBaseView.onGetDataSuccess(resEntity.getData());
//                                   }
//                               } else {
//                                   //获取数据失败
//                                   if (iBaseView!= null) {
//                                       iBaseView.onGetDataFailed("获取数据失败");
//                                   }
//                               }
//                           }
//
//                       } catch (IOException e) {
//                           throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
//                       }
//                   }
//
//                   @Override
//                   public void onError(Throwable e) {
//                       String errorMessage = ErrorUtil.handleError(e);
//                       //获取数据失败
//                       if (iBaseView!= null) {
//                           iBaseView.onGetDataFailed(errorMessage);
//                       }
//                   }
//
//                   @Override
//                   public void onComplete() {
//
//                   }
//               });

        banner.setBannerTitles(Arrays.asList(titles));
        banner.isAutoPlay(true);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });
        banner.start();

    }
    public HashMap<String, String> getParmas() {
        return new HashMap<>();
    }//让子类来提供调用网络请求 的参数
    public HashMap<String, String> getHearerParmas(){
        return new HashMap<>();
    }//让子类来提供调用网络请求的头参数, 例如token
}
