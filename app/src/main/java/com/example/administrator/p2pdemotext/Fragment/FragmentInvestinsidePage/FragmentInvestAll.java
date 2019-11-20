package com.example.administrator.p2pdemotext.Fragment.FragmentInvestinsidePage;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.p2pdemotext.Adapter.InvestAllAdapter;
import com.example.administrator.p2pdemotext.Base.AllBean;
import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.Presenter.InvestAllPresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.Util.PageUtil;
import com.example.base.IBasePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import crazyjone.loadinglibrary.View.LoadingStateWidget;

public class FragmentInvestAll extends BaseFragment<AllBean> {
    private TextView fragmentinvestTextviewPao;
    private RecyclerView fragmentinvestAllRecyclerView;
    PageUtil pageUtil;
//    View inflate;
//    RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
  private RelativeLayout review;


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
        iBasePresenter.ongetHttp();



    }

    @Override
    public void onShow(int code) {
        super.onShow(code);
        if (code==200){
            Log.d("SSH",code+"");
            Toast.makeText(getActivity(), "200", Toast.LENGTH_SHORT).show();
            pageUtil.showLoad();
            //review.addView(inflate,params);

        }else if (code==300){
            Toast.makeText(getActivity(), "300", Toast.LENGTH_SHORT).show();
            pageUtil.hideload();
            //review.removeView(inflate);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        fragmentinvestTextviewPao = (TextView) view.findViewById(R.id.fragmentinvestTextviewPao);
        fragmentinvestAllRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentinvestAllRecyclerView);
        //加载页面
        pageUtil = new PageUtil(getContext());
        review = (RelativeLayout) view.findViewById(R.id.review);
        pageUtil.setReview(review);
//        inflate= LayoutInflater.from(getContext()).inflate(R.layout.loadphoto, null);
//        ImageView imageView=inflate.findViewById(R.id.loadPhotoImageView);
//        Glide.with(getContext()).load(R.mipmap.rongrong_cl).into(imageView);


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
