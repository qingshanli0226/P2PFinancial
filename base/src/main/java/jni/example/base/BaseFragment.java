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

import java.util.List;

import jni.example.common.ConstantMain;
import jni.example.common.NetConnectManager;
import jni.example.common.PageManager;

public abstract class BaseFragment<T> extends Fragment implements IFragment, NetConnectManager.INetConnectListener,IView<T> {

    protected View inflate;
    private RelativeLayout layout;
    protected PageManager pageManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(layoutId(), null);
        init(inflate);
        if(RelativeLayoutID()!=0){
            layout = inflate.findViewById(RelativeLayoutID());
            pageManager = new PageManager(getContext());
            pageManager.setRelativeLayout(layout);
        }

        initData();
        //注册listener，监听当前网络连接的变化
        NetConnectManager.getInstance().registerNetConnectListener(this);
        return inflate;
    }
    public boolean isConnected() {
        return NetConnectManager.getInstance().getConnectStatus();
    }


    @Override
    public void onConnected() {
    }

    @Override
    public void onDisConnected() {
    }

    @Override
    public int RelativeLayoutID() {
        return 0;
    }

    @Override
    public void showLoading() {
        pageManager.showLoading();
    }

    @Override
    public void hideLoading() {
        pageManager.hideLoading();
    }

    @Override
    public void showErrorPage() {
        pageManager.showErrorPage();
    }

    @Override
    public void hideErrorPage() {
        pageManager.hideErrorPage();
    }

    @Override
    public void showNotNetWorkPage() {
        pageManager.showNotNetWorkPage();
    }

    @Override
    public void hideNotNetWorkPage() {
        pageManager.hideNotNetWorkPage();
    }

    @Override
    public void onGetDataFailed(String msg) {

    }

    @Override
    public void onGetDataSuccess(Object data) {

    }

    @Override
    public void onGetDataListSuccess(List data) {

    }

    @Override
    public void onPostDataSuccess(Object data) {

    }

    @Override
    public void onPostDataFailed(String handleError) {

    }
}
