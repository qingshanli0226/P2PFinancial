package com.bwei.p2p.invest.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bwei.base.BaseFragment;
import com.bwei.base.IBasePresenter;
import com.bwei.base.IbaseView;
import com.bwei.base.bean.Products;
import com.bwei.common.P2PError;
import com.bwei.p2p.R;
import com.bwei.p2p.invest.MyRecyclerViewAdapter;
import com.bwei.p2p.presenter.InvestPresenter;
import com.bwei.p2p.util.LoadingAinm;

import java.util.ArrayList;
import java.util.List;

public class InvestAllFragment extends BaseFragment implements IbaseView<Products> {
private TextView quenen;
private RecyclerView recyclerView;
private LoadingAinm loadingAinm;
private IBasePresenter presenter;
private List<Products.DataBean> list;
private MyRecyclerViewAdapter adapterRV;

    @Override
    protected int getLayoutId() {
        return R.layout.invest_all;
    }

    @Override
    protected void initDate() {
        quenen.setSelected(true);
//        下载数据
        presenter.attachView(this);
        presenter.getDate();

    }

    @Override
    protected void initView() {
        quenen = mView.findViewById(R.id.all_quene);
        recyclerView=mView.findViewById(R.id.rv);
        presenter=new InvestPresenter();
        list=new ArrayList<>();
    }

    @Override
    public void onGetDataSucess(Products data) {
//        获取数据
        list.addAll(data.data);
        Toast.makeText(getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
        initRecyclerView();
    }
//recyclerView加载适配
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterRV=new MyRecyclerViewAdapter(list,R.layout.invest_rv_item);
        recyclerView.setAdapter(adapterRV);
    }

    @Override
    public void onGetDataListSucess(List<Products> data) {

    }

    @Override
    public void onGetDataFailed(String message) {
        Toast.makeText(getActivity(), R.string.Failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Log.i("ssss", "showLoading: invest"+loadingAinm);
        loadingAinm=null;
        loadingAinm = new LoadingAinm(getContext(), "正在加载中", R.drawable.anim_loading);
        loadingAinm.setCanceledOnTouchOutside(false);
        loadingAinm.setCancelable(false);
        loadingAinm.show();
        Log.i("ssss", "showLoading: invest"+loadingAinm);
    }

    @Override
    public void hideLoading(int i) {
        if (i == 1) {
            Log.i("ssss", ":失败重试");
            loadingAinm.dismiss();
            loadingAinm = null;
            loadingAinm = new LoadingAinm(getContext(), "网络出差了", R.drawable.ic_error_page);
            loadingAinm.setCanceledOnTouchOutside(false);
            loadingAinm.setCancelable(false);
            loadingAinm.show();
            ImageView img = loadingAinm.findViewById(R.id.dialog_gif);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.getDate();
                }
            });
        }else {
            Log.i("ssss", ":成功关闭");
            if (loadingAinm != null) {
                loadingAinm.dismiss();
                loadingAinm = null;
            }
        }
    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {
        Toast.makeText(mActivity,error.getErrorMessenger(),Toast.LENGTH_SHORT).show();

    }
}
