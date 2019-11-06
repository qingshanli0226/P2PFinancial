package jni.example.p2pinvest.mvp.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.Index;
import jni.example.p2pinvest.mvp.presenter.MainPresenter;
import jni.example.p2pinvest.view.Home_Arc;

public class Fragment_Home extends BaseFragment implements IView<Index> {

    private IPresenter iPresenter;
    private Home_Arc home_arc;
    private Banner banner;
    private ArrayList<String> image_url = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void init(View view) {
        banner = view.findViewById(R.id.home_banner);
        home_arc = view.findViewById(R.id.home_arc);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onGetDataFailed(String msg) {
        Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(Index data) {
        Toast.makeText(getActivity(), "数据请求成功", Toast.LENGTH_SHORT).show();
        List<Index.ImageArrBean> imageArr = data.getImageArr();
        for (int i = 0; i < imageArr.size(); i++) {
            image_url.add(imageArr.get(i).getIMAURL());
        }
        banner.setImages(image_url);
        banner.start();
    }

    @Override
    public void onGetDataListSuccess(List<Index> data) {

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
