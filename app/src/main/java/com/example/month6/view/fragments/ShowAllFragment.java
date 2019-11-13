package com.example.month6.view.fragments;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.diyviews.baseclass.BaseFragmentNetWork;
import com.example.common.diyviews.baseclass.BaseRecyclerViewAdapter;
import com.example.common.diyviews.baseclass.BaseRecyclerViewViewHolder;
import com.example.common.diyviews.presenter.BasePresenter;
import com.example.month6.R;
import com.example.month6.databean.AllShowData;
import com.example.month6.presenter.ShowAllPresenter;
import com.example.month6.view.customviews.CustomProgressView;

import java.util.ArrayList;

import butterknife.BindView;

public class ShowAllFragment extends BaseFragmentNetWork<AllShowData> {
    @BindView(R.id.showAllRecyclerView)
    RecyclerView showAllRecyclerView;

    public ShowAllFragment(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_show_all;
    }

    @Override
    protected BasePresenter<AllShowData> getPresenters() {
        return new ShowAllPresenter();
    }

    @Override
    protected int getViewGroupLayoutId() {
        return R.id.rel;
    }

    @Override
    protected void initView() {
        showAllRecyclerView.setLayoutManager(new LinearLayoutManager(fragmentContext,LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void getDataSuccess(AllShowData object) {
        ArrayList<AllShowData.DataBean> list=new ArrayList<>();
        list.addAll(object.getData());
        showAllRecyclerView.setAdapter(new BaseRecyclerViewAdapter<AllShowData.DataBean>(fragmentContext,list,R.layout.view_show_all_item) {

            @Override
            protected void setViewData(BaseRecyclerViewViewHolder holder, int position) {
                CustomProgressView view_progress = (CustomProgressView) holder.getView(R.id.view_progress);
                view_progress.setNum(Integer.valueOf(list.get(position).getProgress()));

            }
        });
    }
}
