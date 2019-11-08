package jni.example.p2pinvest.mvp.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
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
    private RelativeLayout layout;
    private TextView homeName;
    private TextView homeYearRate;


    //TODO 数据错误页面
    private View errorView;
    //TODO 加载页面
    private View loadView;
    //TODO 加载页面中的图片
    private ImageView imageView;
    //TODO 添加页面的尺寸
    private RelativeLayout.LayoutParams params;
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
        layout = view.findViewById(R.id.home_layout);
        homeName = view.findViewById(R.id.home_name);
        homeYearRate= view.findViewById(R.id.home_yearRate);


        errorView = LayoutInflater.from(getActivity()).inflate(R.layout.page_error, null);
        loadView = LayoutInflater.from(getActivity()).inflate(R.layout.page_loading,null);
        imageView = loadView.findViewById(R.id.load_image);
        Glide.with(getActivity()).load(R.mipmap.rongrong_cl).into(imageView);
        params =new RelativeLayout.LayoutParams(Constant_Main_Home.DIMENS,Constant_Main_Home.DIMENS);
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

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.getData();
                hideErrorPage();
            }
        });
    }

    @Override
    public void showLoading() {
        layout.addView(loadView,params);
    }

    @Override
    public void hideLoading() {
        layout.removeView(loadView);
    }

    @Override
    public void showErrorPage() {
        layout.addView(errorView,params);
    }

    @Override
    public void hideErrorPage() {
        layout.removeView(errorView);
    }

    @Override
    public void onGetDataFailed(String msg) {

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
    public void onGetDataListSuccess(List data) {

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
