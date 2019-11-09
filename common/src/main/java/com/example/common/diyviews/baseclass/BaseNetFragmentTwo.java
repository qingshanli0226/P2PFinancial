package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.common.R;
import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.common.diyviews.presenter.PresenterBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseNetFragmentTwo<T> extends Fragment implements PresenterBaseView<T> {
    protected View fragmentView;
    protected Context fragmentContext;
    protected DiyPresenter<T> diyPresenter;
    private ViewGroup viewGroup;
    View laodView;

    private Unbinder bind;
    public BaseNetFragmentTwo(Context fragmentContext) {
        this.fragmentContext = fragmentContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建视图  绑定控件  P层设置和获取  设置视图  设置数据
        fragmentView= inflater.inflate(getLayoutId(), container, false);
        viewGroup=fragmentView.findViewById(getRelLayoutId());
        bind = ButterKnife.bind(this, fragmentView);
        diyPresenter=getPresenters();
        diyPresenter.setDataView(this);
        diyPresenter.getGetData();
        return fragmentView;
    }
    protected abstract int getLayoutId();
    protected abstract DiyPresenter<T> getPresenters();
    protected abstract int getRelLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showLoadView() {
//        fragmentView.setAlpha(0);
        for (int i=0;i<viewGroup.getChildCount();i++){
            View childAt = viewGroup.getChildAt(i);
            childAt.setVisibility(View.INVISIBLE);
        }
        laodView = LayoutInflater.from(fragmentContext).inflate(R.layout.waitview, viewGroup,false);
        viewGroup.addView(laodView);
    }

    @Override
    public void hindLoadView() {
        viewGroup.removeView(laodView);
        laodView=null;
        for (int i=0;i<viewGroup.getChildCount();i++){
             viewGroup.getChildAt(i).setVisibility(View.VISIBLE);
        }
//        fragmentView.setVisibility(View.VISIBLE);
//        viewGroup.removeView(laodView);
//        laodView=null;
    }

    @Override
    public void findError() {
        fragmentView.setVisibility(View.VISIBLE);
        viewGroup.removeView(laodView);
        laodView=null;

    }

    @Override
    public void setDataError(String str) {
        Log.e("xxxx","错误");
    }


    @Override
    public void onDestroyView() {
        if (bind!=null){
            bind.unbind();
        }
//        loadView=null;
        diyPresenter.destoryDataView();
        super.onDestroyView();
    }
}
