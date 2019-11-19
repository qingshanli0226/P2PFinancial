package com.example.administrator.p2pdemotext.Fragment.FragmentInvestinsidePage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Adapter.InvestAllAdapter;
import com.example.administrator.p2pdemotext.Base.AllBean;
import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.Presenter.InvestAllPresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.base.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

public class FragmentInvestAll extends BaseFragment<AllBean> {
    private TextView fragmentinvestTextviewPao;
    private RecyclerView fragmentinvestAllRecyclerView;
    InvestAllAdapter adp;
    IBasePresenter iBasePresenter;
    ArrayList<AllBean.DataBean> arr=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investall;
    }

    @Override
    protected void initData() {
        super.initData();
        fragmentinvestTextviewPao.setSelected(true);
        iBasePresenter=new InvestAllPresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData();



    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        fragmentinvestTextviewPao = (TextView) view.findViewById(R.id.fragmentinvestTextviewPao);
        fragmentinvestAllRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentinvestAllRecyclerView);

    }
    //获取到的数据


    @Override
    public void onGetDataSucess(AllBean data) {
        super.onGetDataSucess(data);
        List<AllBean.DataBean> base = data.getData();
        arr.addAll(base);
        adp=new InvestAllAdapter(arr,getContext());
        fragmentinvestAllRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentinvestAllRecyclerView.setAdapter(adp);
        adp.setHuidiao(new InvestAllAdapter.Huidiao() {
            @Override
            public void hui(int i) {
                Toast.makeText(getContext(), "你点击了第"+i+"条数据", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initTopTitle() {

    }
}
