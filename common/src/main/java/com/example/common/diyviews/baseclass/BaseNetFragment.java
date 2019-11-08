package com.example.common.diyviews.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.common.R;
import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.common.diyviews.presenter.PresenterBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseNetFragment<T> extends Fragment implements PresenterBaseView<T> {
    protected View fragmentView;
    protected Context fragmentContext;
    protected DiyPresenter<T> diyPresenter;
    AlertDialog dialog;
    private View loadView;

    private Unbinder bind;
    public BaseNetFragment(Context fragmentContext) {
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
        initView();
        return fragmentView;
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract DiyPresenter<T> getPresenters();

    @Override
    public void showLoadView() {
        //加载提示用对话框表示
        loadView = LayoutInflater.from(fragmentContext).inflate(R.layout.waitview, null);
        dialog = new AlertDialog.Builder(fragmentContext)
                .setCancelable(false)
                .setView(loadView)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.backalpha);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void setDataError(String str) {
        Log.e("xxxx","错误");
    }


    @Override
    public void hindLoadView() {
        dialog.dismiss();
    }

    @Override
    public void findError() {
        Log.e("xxxx","发现错误");
        dialog = new AlertDialog.Builder(fragmentContext)
                .setCancelable(false)
                .setView(R.layout.errorview)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.backalpha);
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        if (bind!=null){
            bind.unbind();
        }
        loadView=null;
        diyPresenter.destoryDataView();
        super.onDestroyView();
    }
}
