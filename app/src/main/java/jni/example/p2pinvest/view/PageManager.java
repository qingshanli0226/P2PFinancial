package jni.example.p2pinvest.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import jni.example.common.Constant;
import jni.example.p2pinvest.R;

public class PageManager {
    private static PageManager pageManager;
    private RelativeLayout relativeLayout;
    private Context context;
    private boolean isLoading = false;
    private boolean isError = false;
    private boolean isNotNetWork = false;
    private RelativeLayout.LayoutParams params;
    private View loadView;
    private View errorView;
    private View notNetWorkView;
    private PageOnClickListener listener;

    public PageOnClickListener getListener() {
        return listener;
    }

    public void setListener(PageOnClickListener listener) {
        this.listener = listener;
    }

    private PageManager(Context context, RelativeLayout relativeLayout) {
        this.context =context;
        this.relativeLayout = relativeLayout;
        init();
    }

    public static PageManager getInstance(Context context,RelativeLayout relativeLayout){
        if(pageManager==null){
            pageManager = new PageManager(context,relativeLayout);
        }
        return pageManager;
    }

    public void init(){
        loadView = LayoutInflater.from(context).inflate(R.layout.page_loading, null);
        errorView = LayoutInflater.from(context).inflate(R.layout.page_error, null);
        notNetWorkView = LayoutInflater.from(context).inflate(R.layout.page_not_net, null);
        ImageView imageView = loadView.findViewById(R.id.load_image);
        Glide.with(context).load(R.mipmap.rongrong_cl).into(imageView);
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListener().onClickErrorPage();
            }
        });
    }


    public void showLoading() {
        if(!isLoading){
            relativeLayout.addView(loadView,params);
            isLoading = true;
        }
    }

    public void hideLoading() {
        if(isLoading){
            relativeLayout.removeView(loadView);
            isLoading = false;
        }
    }

    public void showErrorPage() {
        if(!isError){
            relativeLayout.addView(errorView,params);
            isError = true;
        }
    }

    public void hideErrorPage() {
        if(isError){
            relativeLayout.removeView(errorView);
            isError = false;
        }
    }

    public void showNotNetWorkPage() {
        if(!isNotNetWork){
            relativeLayout.addView(notNetWorkView,params);
            isNotNetWork = true;
        }
    }

    public void hideNotNetWorkPage() {
        if(isNotNetWork){
            relativeLayout.removeView(notNetWorkView);
            isNotNetWork = false;
        }
    }

    public interface PageOnClickListener{
        void onClickErrorPage();
    }


}
