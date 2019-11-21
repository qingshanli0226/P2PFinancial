package jni.example.p2pinvest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import jni.example.base.BaseCustomView;
import jni.example.common.ConstantTopBar;
import jni.example.p2pinvest.R;

public class TopBar extends BaseCustomView {
    private TextView topBarText;
    private ImageView back;
    private ImageView setting;

    private onMyClicklistener onMyClicklistener;

    public TopBar.onMyClicklistener getOnMyClicklistener() {
        return onMyClicklistener;
    }

    public void setOnMyClicklistener(TopBar.onMyClicklistener onMyClicklistener) {
        this.onMyClicklistener = onMyClicklistener;
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int LayoutId() {
        return R.layout.top_bar;
    }

    @Override
    public void init() {
        topBarText = (TextView) findViewById(R.id.top_bar_text);
        setting = (ImageView) findViewById(R.id.top_bar_title_setting);
        back = (ImageView) findViewById(R.id.top_bar_title_back);
        conceal_image();
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnMyClicklistener().onClick();
            }
        });
    }

    //TODO 设置fragment切换后修改topBar的内容
    public void setTopBarText(int index) {
        switch (index){
            case 0:
                conceal_image();
                topBarText.setText(getResources().getString(R.string.main_home));
                break;
            case 1:
                conceal_image();
                topBarText.setText(getResources().getString(R.string.main_invest));
                break;
            case 2:
                conceal_image();
                setting.setVisibility(VISIBLE);
                topBarText.setText(getResources().getString(R.string.main_assets));
                break;
            case 3:
                conceal_image();
                topBarText.setText(getResources().getString(R.string.main_more));
                break;
            case 4:
                topBarText.setText(getResources().getString(R.string.top_bar_login_text));
                back.setVisibility(VISIBLE);
                break;
            case 5:
                topBarText.setText(getResources().getString(R.string.top_bar_register_text));
                back.setVisibility(VISIBLE);
                break;
        }
    }


    public void conceal_image(){
        setting.setVisibility(GONE);
        back.setVisibility(GONE);
    }

    public interface onMyClicklistener{
        void onClick();
    }
}
