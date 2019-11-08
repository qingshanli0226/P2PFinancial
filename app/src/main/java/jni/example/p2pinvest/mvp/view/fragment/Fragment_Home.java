package jni.example.p2pinvest.mvp.view.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jni.example.base.BaseFragment;
import jni.example.base.IPresenter;
import jni.example.base.IView;
import jni.example.common.Constant_Main_Home;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.Index;
import jni.example.p2pinvest.mvp.presenter.MainPresenter;
import jni.example.p2pinvest.view.Home_Arc;

public class Fragment_Home extends BaseFragment implements IView<Index> {

    //TODO P层接口
    private IPresenter iPresenter;
    //TODO 首页中的圆弧自定义View
    private Home_Arc home_arc;
    //TODO banner轮播图
    private Banner banner;
    //TODO 存放轮播图 图片集合
    private ArrayList<String> image_url = new ArrayList<>();
    //TODO 当前进度
    private int currentProgress;
    private ImageView imageView;
    private TextView homeName;
    private TextView homeYearRate;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == Constant_Main_Home.INDEX) {
                Index data = (Index) msg.obj;
                List<Index.ImageArrBean> imageArr = data.getImageArr();
                for (int i = 0; i < imageArr.size(); i++) {
                    image_url.add(imageArr.get(i).getIMAURL());
                }
                banner.setImages(image_url);
                banner.start();
                homeName.setText(data.getProInfo().getName());
                homeYearRate.setText(homeYearRate.getText()+data.getProInfo().getYearRate()+"%");
                currentProgress = Integer.parseInt(data.getProInfo().getProgress());
                new Thread(runnable).start();
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= currentProgress; i++) {
                home_arc.setProgress(i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //TODO 主线程、子线程都可以调用
                home_arc.postInvalidate();


            }
        }
    };


    //TODO 当前Fragment布局ID
    @Override
    public int layoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void init(View view) {
        banner = view.findViewById(R.id.home_banner);
        home_arc = view.findViewById(R.id.home_arc);
        homeName = (TextView) view.findViewById(R.id.home_name);
        homeYearRate= view.findViewById(R.id.home_yearRate);
        imageView = view.findViewById(R.id.home_load_image);
        Glide.with(getActivity()).load(R.mipmap.rongrong_cl).into(imageView);
    }

    @Override
    public void initData(View view) {
        iPresenter = new MainPresenter();
        iPresenter.attachView(this);

        iPresenter.getData();
        banner.setDelayTime(2000);
        banner.setBannerAnimation(Transformer.ZoomIn);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
        banner.setBannerTitles(Arrays.asList(titles));

    }

    @Override
    public void showLoading() {
        imageView.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "开始加载动画", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        imageView.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "结束加载动画", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataFailed(String msg) {
        Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(Index data) {
        Message message = new Message();
        message.what = Constant_Main_Home.INDEX;
        message.obj = data;
        handler.sendMessage(message);
        Toast.makeText(getActivity(), "数据请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListSuccess(List<Index> data) {

    }

    private void initView() {

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
