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
import com.example.common.diyviews.presenter.BasePresenter;
import com.example.common.diyviews.presenter.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragmentDialog<T> extends BaseFragment implements IBaseView<T> {

    protected BasePresenter<T> presenter;
    AlertDialog dialog;
    private View loadView;
    private Unbinder bind;

    public BaseFragmentDialog(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected void initData() {
        presenter=getPresenters();
        presenter.setIBaseView(this);
        presenter.sendGetRequest();
    }

    protected abstract int getLayoutId();
    protected abstract BasePresenter<T> getPresenters();

    @Override
    public void showLoadView() {
        //加载提示用对话框表示
        loadView = LayoutInflater.from(fragmentContext).inflate(R.layout.custom_waitview, null);
        dialog = new AlertDialog.Builder(fragmentContext)
                .setCancelable(false)
                .setView(loadView)
                .create();
        //
        dialog.getWindow().setBackgroundDrawableResource(R.color.backalpha);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void hideLoadView() {
        dialog.dismiss();
        loadView=null;
    }
    //
    @Override
    public void getDataError() {
        Log.e("xxxx","发现错误");
        dialog = new AlertDialog.Builder(fragmentContext)
                .setCancelable(false)
                .setView(R.layout.custom_errorview)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.backalpha);
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        if (bind!=null){
            bind.unbind();
        }
        presenter.destoryIBaseView();
        super.onDestroyView();
    }
}
