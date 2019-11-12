package com.example.p2pdemo.Fragment.InvestFragment;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.p2pdemo.Adapter.ProduceAdapter;
import com.example.p2pdemo.Bean.InvestBean;
import com.example.p2pdemo.BuildConfig;
import com.example.p2pdemo.Presenter.InvestPresenter;
import com.example.p2pdemo.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class AllMoneyFragment extends BaseFragment implements IBaseView<InvestBean> {

    private View view;
    private RelativeLayout relativeLayout;
    private GifImageView gifImageView;
    private ListView listView;
    @Override
    protected void inItData(View view1) {

                      view=view1;
                   relativeLayout=view1.findViewById(R.id.More_rel);
                   gifImageView=view1.findViewById(R.id.More_gif);
                   listView=view1.findViewById(R.id.Produce_ListView);

                    InvestPresenter investPresenter = new InvestPresenter();
                    investPresenter.attachView(this);
                    investPresenter.getData();
    }
    @Override
    protected int setView() {
        return R.layout.more;
    }


    @Override
    public void onGetDataSucess(int resultCode,InvestBean data) {
        if(resultCode==100){
            if(data!=null){
                ProduceAdapter produceAdapter = new ProduceAdapter(getContext(), data);
                listView.setAdapter(produceAdapter
                );

            }else{
                Toast.makeText(getContext(), "当前数据为空", Toast.LENGTH_SHORT).show();
            }
        }


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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gifImageView.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        },2000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new InvestPresenter().detchView();
    }
}
