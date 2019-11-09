package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.common.R;
import com.example.common.diyviews.presenter.BasePresenter;
import com.example.common.diyviews.presenter.IBaseView;

import butterknife.BindView;
import butterknife.Unbinder;

public abstract class BaseFragmentNetWork<T> extends BaseFragment implements IBaseView<T> {

    protected BasePresenter<T> presenter;
    private ViewGroup viewGroup;
    private View laodView;

    private Unbinder bind;

    public BaseFragmentNetWork(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected void initData() {
        viewGroup = fragmentView.findViewById(getViewGroupLayoutId());
        presenter = getPresenters();
        presenter.setIBaseView(this);
        presenter.sendGetRequest();
    }

    protected abstract int getLayoutId();

    protected abstract BasePresenter<T> getPresenters();

    protected abstract int getViewGroupLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showLoadView() {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setVisibility(View.INVISIBLE);
        }
        laodView=null;
        laodView = LayoutInflater.from(fragmentContext).inflate(R.layout.custom_waitview, viewGroup, false);
        viewGroup.addView(laodView);
    }

    @Override
    public void hideLoadView() {
        viewGroup.removeView(laodView);
        laodView = null;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getDataError() {
        viewGroup.removeView(laodView);
        laodView = null;
        laodView = LayoutInflater.from(fragmentContext).inflate(R.layout.custom_errorview, viewGroup, false);
        ImageView errorImg = laodView.findViewById(R.id.errorImg);
        errorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendGetRequest();
            }
        });
        viewGroup.addView(laodView);
    }

    @Override
    public void onDestroyView() {
        if (bind != null) {
            bind.unbind();
        }
        presenter.destoryIBaseView();
        super.onDestroyView();
    }
}
