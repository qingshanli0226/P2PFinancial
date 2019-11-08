package com.example.p2pdemo.Fragment.InvestFragment;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.p2pdemo.Bean.InvestBean;
import com.example.p2pdemo.Presenter.InvestPresenter;
import com.example.p2pdemo.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class AllMoneyFragment extends BaseFragment implements IBaseView<InvestBean> {

    private RelativeLayout relativeLayout;
    private GifImageView gifImageView;
    @Override
    protected void inItData(View view1) {

        relativeLayout=view1.findViewById(R.id.More_rel);
        gifImageView=view1.findViewById(R.id.More_gif);
        InvestPresenter investPresenter = new InvestPresenter();
        investPresenter.attachView(this);
        investPresenter.getInvestData();


    }

    @Override
    protected int setView() {
        return R.layout.more;
    }


    @Override
    public void onGetDataSucess(InvestBean data) {

    }

    @Override
    public void onGetDataListSucess(List<InvestBean> data) {
        Log.e("##",data.toString());
    }

    @Override
    public void onGetDataFiled(String fileMess) {

    }

    @Override
    public void loadView() {

    }

    @Override
    public void unLoadView() {
        gifImageView.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new InvestPresenter().detchView();
    }
}
