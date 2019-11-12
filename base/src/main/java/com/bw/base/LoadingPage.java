package com.bw.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.bw.common.UIUtils;
import com.loopj.android.http.RequestParams;

public abstract class LoadingPage extends FrameLayout {

    //定义四种不同的显示方式
    private static final int STATE_LOADING = 1;
    private static final int STATE_ERROR = 2;
    private static final int STATE_EMPTY = 3;
    private static final int STATE_SUCCESS = 4;

    //默认情况下，当前状态为正在加载
    private int state_current = STATE_LOADING;

    //提供四种不同的界面
    private View view_loading;
    private View view_error;
    private View view_empty;
    private View view_success;
    private LayoutParams params;

    private Context context;

    public LoadingPage( Context context) {
        super(context,null);
    }

    public LoadingPage( Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public LoadingPage( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        init();
    }

    //初始化方法
    private void init() {
        //实例化view
        //提供布局显示的参数
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        if (view_loading == null){
            //加载布局
            view_loading = UIUtils.getView(R.layout.page_loading);
            //添加到当前的frameLayout
            addView(view_loading,params);
        }
        if (view_empty == null){
            //加载布局
            view_empty = UIUtils.getView(R.layout.page_empty);
            //添加到当前的frameLayout
            addView(view_empty,params);
        }
        if (view_error == null){
            //加载布局
            view_error = UIUtils.getView(R.layout.page_error);
            //添加到当前的frameLayout
            addView(view_error,params);
        }

        showSafePage();
    }

    //保证如下的操作在主线程中执行的，更新界面
    private void showSafePage() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //保证run()中的操作在主线程中执行
                showPage();
            }
        });
    }

    private void showPage() {
        //根据当前state_current的值，决定显示哪个view
        view_loading.setVisibility(state_current == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        view_error.setVisibility(state_current == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        view_empty.setVisibility(state_current == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);

        if (view_success == null){
            //加载布局使用的是activity
            view_success = View.inflate(context,layoutId(),null);
            addView(view_success,params);
        }

        view_success.setVisibility(state_current == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

    protected abstract int layoutId();

    private ResultState resultState;

    //在show()中实现联网加载数据
    public void show(){

        String url = url();
        if (TextUtils.isEmpty(url)){
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            //修改state_current，并且决定加载哪个页面：showSafePage()
            loadImage();
            return;
        }

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);

    }

    protected void loadImage(){
        switch (resultState){
            case ERROR:
                state_current = STATE_ERROR;
                break;
            case EMPTY:
                state_current = STATE_EMPTY;
                break;
            case SUCCESS:
                state_current = STATE_SUCCESS;
                break;
        }
        //根据修改以后的state_current，更新视图展示
        showSafePage();

        if (state_current == STATE_SUCCESS){
            onSuccess(resultState,view_success);
        }

    }

    protected abstract void onSuccess(ResultState resultState, View view_success);

    //提供联网的请求参数
    protected abstract RequestParams params();

    //提供联网的请求地址
    protected abstract String url();

    //提供枚举类，封装联网以后的状态值和数据
    public enum  ResultState {

        ERROR(2), EMPTY(3), SUCCESS(4);

        int state;

        ResultState(int state) {
            this.state = state;
        }

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
