package com.example.administrator.p2pdemotext.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Util.PageUtil;
import com.example.base.IBaseView;

import java.util.List;

import crazyjone.loadinglibrary.View.LoadingStateWidget;

public abstract class BaseFragment<T> extends Fragment implements IBaseView<T> {
   // LoadingStateWidget load=new LoadingStateWidget();
    RelativeLayout relativeLayout;
    PageUtil pageUtil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       pageUtil=new PageUtil(getActivity());
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        //load.Attach(getActivity());
       if (getRvid()!=0){
         relativeLayout= view.findViewById(getRvid());
         pageUtil.setReview(relativeLayout);
       }

        initData();
        initTopTitle();


    }


    //初始化数据
    protected void initData(){

    }

    @Override
    public void onShow(int code) {
//       if (code==200){
//          pageUtil.showLoad();
//       }else if (code==300){
//           pageUtil.hideload();
//       }

    }

    //提供布局
    @LayoutRes
    protected abstract int getLayoutId();


    protected  int getRvid(){
      return 0;
    }


 //初始化View
    protected abstract void initView(View view, Bundle savedInstanceState);

    //初始化标题栏
    protected abstract void initTopTitle();
    @Override
    public void onGetDataSucess(T data) {

    }

    @Override
    public void onGetDataListSucess(List<T> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, T data) {


    }

    @Override
    public void onHttpRequestDataFailed(int requestCode) {

    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<T> data) {

    }
}
