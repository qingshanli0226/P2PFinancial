package com.example.p2pdemo.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.p2pdemo.Presenter.HomePresenter;
import com.example.p2pdemo.R;

import pl.droidsonroids.gif.GifImageView;

public class MyErrorPage extends LinearLayout {
    GifImageView gifImageView;
    Button b_refresh;

    MyErrorPage context=this;
    public MyErrorPage(Context context) {
        super(context);
    }

    public MyErrorPage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.erropage, this);
        b_refresh=view.findViewById(R.id.but_errorRefresh);
        gifImageView=view.findViewById(R.id.Error_gifImg);




    }

    public MyErrorPage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void init(final IBasePresenter iBasePresenter1){
        b_refresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ErroStart(iBasePresenter1);
            }
        });

    }
     public void ErroStart(IBasePresenter iBasePresenters){
        if(iBasePresenters!=null){
            iBasePresenters.attachView((IBaseView) context);
            iBasePresenters.getData();
        }
     }

}
