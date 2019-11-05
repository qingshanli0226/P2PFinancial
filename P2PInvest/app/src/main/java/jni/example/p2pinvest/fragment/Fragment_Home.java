package jni.example.p2pinvest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import jni.example.lib_core.base.BaseFragment;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.view.Home_Arc;

public class Fragment_Home extends BaseFragment {

    private Home_Arc home_arc;
    private Banner banner;
    private ArrayList<Integer> image_url = new ArrayList<>();
    @Override
    public int layoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void init(View view) {
        banner = view.findViewById(R.id.home_banner);
        home_arc = view.findViewById(R.id.home_arc);
        image_url.add(R.mipmap.honghong);
        image_url.add(R.mipmap.tu2);
        image_url.add(R.mipmap.tu3);
        banner.setImages(image_url);
        banner.setDelayTime(2000);
        banner.setBannerAnimation(Transformer.ZoomIn);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(1);
        banner.start();
    }

    @Override
    public void initData(View view) {

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
