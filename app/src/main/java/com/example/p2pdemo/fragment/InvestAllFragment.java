package com.example.p2pdemo.fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.example.modulebase.BaseFragment;
import com.example.modulebase.IBasePresenter;
import com.example.modulebase.IBaseView;
import com.example.modulecommon.AppNetConfig;
import com.example.p2pdemo.R;
import com.example.p2pdemo.adpter.InvestAllAdapter;
import com.example.p2pdemo.bean.ProductBean;
import com.example.p2pdemo.presenter.InvestAllPresenter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;

public class InvestAllFragment extends BaseFragment implements IBaseView<Object> {
    @BindView(R.id.investAll_title)
    TextView investallTitle;
    @BindView(R.id.investAll_lv)
    ListView investallLv;
    @BindView(R.id.investAll_loading_view)
    AVLoadingIndicatorView loadingView;
    private IBasePresenter investAllPresenter;
    private InvestAllAdapter allAdapter;

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        investAllPresenter = new InvestAllPresenter();
        investAllPresenter.attachView(this);
        investAllPresenter.doHttpRequest(AppNetConfig.INVEST_ALL_CODE);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_investall;
    }

    @Override
    public void onLoadDataSuccess(int requestCode, Object data) {
        if (requestCode == AppNetConfig.INVEST_ALL_CODE) {
            ProductBean productBean = (ProductBean) data;
            allAdapter = new InvestAllAdapter(productBean.getData());
            investallLv.setAdapter(allAdapter);
        }
    }

    @Override
    public void onLoadDataListSuccess(int requestCode, List<Object> data) {

    }

    @Override
    public void showLoading(int requestCode) {
        loadingView.show();
    }

    @Override
    public void hideLoading(int requestCode) {
        loadingView.hide();
    }
}
