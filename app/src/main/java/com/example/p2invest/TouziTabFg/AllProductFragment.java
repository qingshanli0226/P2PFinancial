package com.example.p2invest.TouziTabFg;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.base.IBaseView;
import com.example.base.IBsePresenter;
import com.example.net.ProductData;
import com.example.p2invest.Adpter.BaseRecycleAdpter;
import com.example.p2invest.R;
import com.example.p2invest.presenter.ZiChanPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AllProductFragment extends BaseFragment  implements IBaseView<ProductData> {
    private RecyclerView allRecycle;
    private IBsePresenter iBsePresenter;
    private ArrayList<ProductData.DataBean> productData;
    private BaseRecycleAdpter baseRecycleAdpter;
    @Override
    protected void initData() {
        if (!isConnected()){
            Toast.makeText(getActivity(), "当前没有网络连接", Toast.LENGTH_SHORT).show();
            return;
        }
    //    Toast.makeText(getContext(), "当前网络连接正常，获取数据", Toast.LENGTH_SHORT).show();
        iBsePresenter = new ZiChanPresenter();
        iBsePresenter.attachView(this);
        iBsePresenter.getData();

    }

    @Override
    public void initView() {
        allRecycle=view.findViewById(R.id.allRecycle);

        allRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.touzi_allfragment;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

    @Override
    public void onGetDataSucces(ProductData data) {
        productData=new ArrayList<>();
        Log.i("pro", "pro: "+data.toString());
        boolean success = data.isSuccess();
        if (success){
//            ProductData pro = new Gson().fromJson(data.toString(), ProductData.class);
//
//            productData.addAll(pro.getData());
//            baseRecycleAdpter=new BaseRecycleAdpter(R.layout.allproduct_item,productData);
//            allRecycle.setAdapter(baseRecycleAdpter);
        }
    }

    @Override
    public void onGetDataListSuccess(List<ProductData> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "showLoading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(getActivity(), "hideLoading", Toast.LENGTH_SHORT).show();
    }
}
