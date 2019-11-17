package com.example.p2pdemo.Fragment.InvestFragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseFragment;
import com.example.base.IBaseView;
import com.example.p2pdemo.Adapter.ProduceAdapter;
import com.example.p2pdemo.Bean.InvestBean;
import com.example.p2pdemo.Presenter.InvestPresenter;
import com.example.p2pdemo.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class AllMoneyFragment extends BaseFragment implements IBaseView<InvestBean> {

    private View view;
    private RelativeLayout relativeLayout;
    private GifImageView gifImageView;
    private ListView listView;
    AnimationDrawable background;

    @Override
    protected int setView() {
        return R.layout.allmoney_fragment;

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
        background.stop();
    }

    @Override
    protected void inItData() {

        relativeLayout=getBaseView().findViewById(R.id.More_rel);
        gifImageView=getBaseView().findViewById(R.id.More_gif);
        listView=getBaseView().findViewById(R.id.Produce_ListView);
        TextView marquee = getBaseView().findViewById(R.id.Marquee_text);
         background =(AnimationDrawable) marquee.getBackground();
        background.start();



        InvestPresenter investPresenter = new InvestPresenter();
        investPresenter.attachView(this);
        investPresenter.getData();
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
