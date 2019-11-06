package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.common.diyviews.presenter.PresenterBaseView;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T> extends Fragment implements PresenterBaseView<T> {
    protected View fragmentView;
    protected Context fragmentContext;
    protected DiyPresenter<T> diyPresenter;

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
        diyPresenter=new DiyPresenter<T>() {
            @Override
            protected Type getDataClass() {
                return getFragDataClass();
            }
        };
        diyPresenter.setDataView(this);
        diyPresenter.getData();
        return fragmentView;
    }

    protected abstract int getLayoutId();
    protected abstract Type getFragDataClass();

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
        Log.e("xxxx","等待");
    }

    @Override
    public void hindLoadView() {
        Log.e("xxxx","隐藏");
    }

    @Override
    public void findError() {
        Log.e("xxxx","发现错误");
    }
}
