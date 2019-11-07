package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.common.R;
import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.common.diyviews.presenter.PresenterBaseView;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T> extends Fragment implements PresenterBaseView<T> {
    protected View fragmentView;
    protected Context fragmentContext;
    protected DiyPresenter<T> diyPresenter;
    AlertDialog dialog;

    private Unbinder bind;
    public BaseFragment(Context fragmentContext) {
        this.fragmentContext = fragmentContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建视图  获取上下文  绑定控件  P层设置和获取  设置视图  设置数据
        fragmentView= inflater.inflate(getLayoutId(), container, false);
        fragmentContext=getContext();
        bind = ButterKnife.bind(this, fragmentView);
        diyPresenter=getPresenters();
        diyPresenter.setDataView(this);
        diyPresenter.getData();
        return fragmentView;
    }

    protected abstract int getLayoutId();
    protected abstract DiyPresenter<T> getPresenters();
    //如果请求网络数据,则重写
    protected int getloadId(){
        return 0;
    }
    protected int getbackColor(){
        return 0;
    }
    @Override
    public void onDestroyView() {
        if (bind!=null){
            bind.unbind();
        }
        diyPresenter.destoryDataView();
        super.onDestroyView();
    }

    @Override
    public void setDataError(String str) {
        Log.e("xxxx","错误");
    }

    @Override
    public void showLoadView() {
        dialog = new AlertDialog.Builder(fragmentContext)
                .setCancelable(false)
                .setView(getloadId())
                .create();
        dialog.getWindow().setBackgroundDrawableResource(getbackColor());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void hindLoadView() {
        dialog.dismiss();
//        diyPresenter.getData();
    }

    @Override
    public void findError() {
        Log.e("xxxx","发现错误");
    }
}
