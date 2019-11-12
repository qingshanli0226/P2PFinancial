package jni.example.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import jni.example.common.ConstantMainHome;
import jni.example.common.NetConnectManager;

public abstract class BaseFragment extends Fragment implements IFragment, NetConnectManager.INetConnectListener {

    protected View inflate;

    private View loadView;
    private ImageView imageView;
    private RelativeLayout.LayoutParams params;
    private ViewGroup group;
    //TODO 是否正在加载
    private boolean isLoading = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(layoutId(), null);
        init(inflate);
        loadView = LayoutInflater.from(getActivity()).inflate(R.layout.page_loading,null);
        imageView = loadView.findViewById(R.id.load_image);
        Glide.with(getActivity()).load(R.mipmap.rongrong_cl).into(imageView);
        params =new RelativeLayout.LayoutParams(ConstantMainHome.DIMENS, ConstantMainHome.DIMENS);
        group = container;
        initData();
        //注册listener，监听当前网络连接的变化
        NetConnectManager.getInstance().registerNetConnectListener(this);
        return inflate;
    }
    public boolean isConnected() {
        return NetConnectManager.getInstance().getConnectStatus();
    }

//    @Override
//    public void showLoading() {
//        if(!isLoading){
//            group.addView(loadView,params);
//            isLoading = true;
//        }
//    }
//
//    @Override
//    public void hideLoading() {
//        if(isLoading){
//            group.removeView(loadView);
//            isLoading = false;
//        }
//    }

    @Override
    public void onConnected() {
    }

    @Override
    public void onDisConnected() {
    }
}
