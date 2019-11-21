package jni.example.p2pinvest.mvp.view.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import jni.example.common.ConstantMain;
import jni.example.common.PageManager;
import jni.example.p2pinvest.manager.CaCheManager;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.Index;
import jni.example.p2pinvest.view.MyProgressBar;

public class HomeFragment extends BaseFragment implements CaCheManager.GetDateListener {

    //TODO P层接口
    private IPresenter iPresenter;
    //TODO 首页中的圆弧自定义View
    private MyProgressBar myProgressBar;
    //TODO banner轮播图
    private Banner banner;
    //TODO 存放轮播图 图片集合
    private ArrayList<String> image_url = new ArrayList<>();
    //TODO 当前进度
    private int currentProgress;
    private TextView homeName;
    private TextView homeYearRate;

    //TODO 首页数据
    private Index data;
    //TODO 轮播图文字内容
    private String[] titles;
    //TODO 线程
    private Thread thread;

    //TODO 数据管理类
    private CaCheManager caCheManager;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == ConstantMain.INDEX) {
                data = (Index) msg.obj;
                image_url.clear();
                thread = null;
                List<Index.ImageArrBean> imageArr = data.getImageArr();
                for (int i = 0; i < imageArr.size(); i++) {
                    image_url.add(imageArr.get(i).getIMAURL());
                }
                banner.setImages(image_url);
                banner.start();
                homeName.setText(data.getProInfo().getName());
                homeYearRate.setText(getResources().getString(R.string.home_text_year_rate)+data.getProInfo().getYearRate()+"%");
                currentProgress = Integer.parseInt(data.getProInfo().getProgress());
                thread = new Thread(runnable);
                thread.start();
            }
        }
    };

    //TODO 当前Fragment布局ID
    @Override
    public int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public int RelativeLayoutID() {
        return R.id.home_layout;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= currentProgress; i++) {
                myProgressBar.setProgress(i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //TODO 尽量在主线程
                myProgressBar.postInvalidate();
            }
        }
    };

    @Override
    public void init(View view) {
        banner = view.findViewById(R.id.home_banner);
        myProgressBar = view.findViewById(R.id.home_arc);
        homeName = view.findViewById(R.id.home_name);
        homeYearRate= view.findViewById(R.id.home_yearRate);
        caCheManager = CaCheManager.getInstance();

    }


    public void initData() {
        if(!isConnected()){
            showNotNetWorkPage();
            hideLoading();
            hideErrorPage();
            Toast.makeText(getActivity(), "当前无网络", Toast.LENGTH_SHORT).show();
            return;
        }
        hideNotNetWorkPage();
        Toast.makeText(getActivity(), "有网络了", Toast.LENGTH_SHORT).show();

        caCheManager.registerGetDateListener(this);
        Object object = caCheManager.getObject();
        Message message = new Message();
        message.what = ConstantMain.INDEX;
        message.obj = object;
        handler.sendMessage(message);
        Log.d("lhf","服务获取数据");

        banner.setDelayTime(2000);
        banner.setBannerAnimation(Transformer.ZoomIn);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        titles = new String[]{getResources().getString(R.string.home_banner_text_one),
                getResources().getString(R.string.home_banner_text_two),
                getResources().getString(R.string.home_banner_text_three),
                getResources().getString(R.string.home_banner_text_four)};
        banner.setBannerTitles(Arrays.asList(titles));
        this.pageManager.setListener(new PageManager.PageOnClickListener() {
            @Override
            public void onClickErrorPage() {
                iPresenter.getData();
                hideErrorPage();
            }
        });
    }

    @Override
    public void onGetDataSuccess(Object data) {
        Message message = new Message();
        message.what = ConstantMain.INDEX;
        message.obj = data;
        handler.sendMessage(message);
    }

    @Override
    public void onGetIndex(Index index) {
        Log.d("lhf","监听从服务拿数据");
        Message message = new Message();
        message.what = ConstantMain.INDEX;
        message.obj = index;
        handler.sendMessage(message);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public void onConnected() {
        super.onConnected();
        initData();
        hideNotNetWorkPage();
    }

    @Override
    public void onDisConnected() {
        super.onDisConnected();
        showNotNetWorkPage();
        Toast.makeText(getActivity(), "当前网络连接已经断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        caCheManager.unRegisterGetDateListener(this);
    }
}
