package jni.example.p2pinvest.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import jni.example.p2pinvest.R;

public class PageManager {
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

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public PageOnClickListener getListener() {
        return listener;
    }

    public void setListener(PageOnClickListener listener) {
        this.listener = listener;
    }

    public PageManager(Context context) {
        this.context =context;
        init();
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
            getRelativeLayout().addView(loadView,params);
            isLoading = true;
        }
    }

    public void hideLoading() {
        if(isLoading){
            getRelativeLayout().removeView(loadView);
            isLoading = false;
        }
    }

    public void showErrorPage() {
        if(!isError){
            getRelativeLayout().addView(errorView,params);
            isError = true;
        }
    }

    public void hideErrorPage() {
        if(isError){
            getRelativeLayout().removeView(errorView);
            isError = false;
        }
    }

    public void showNotNetWorkPage() {
        if(!isNotNetWork){
            getRelativeLayout().addView(notNetWorkView,params);
            isNotNetWork = true;
        }
    }

    public void hideNotNetWorkPage() {
        if(isNotNetWork){
            getRelativeLayout().removeView(notNetWorkView);
            isNotNetWork = false;
        }
    }

//    public void removeAll(){
//        if(isLoading){
//            getRelativeLayout().removeView(loadView);
//            isLoading = false;
//        }
//        if(isError){
//            getRelativeLayout().removeView(errorView);
//            isError = false;
//        }
//        if(isNotNetWork){
//            getRelativeLayout().removeView(notNetWorkView);
//            isNotNetWork = false;
//        }
//    }

    public interface PageOnClickListener{
        void onClickErrorPage();
    }


}
