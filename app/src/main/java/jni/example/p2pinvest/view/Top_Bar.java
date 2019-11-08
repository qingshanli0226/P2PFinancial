package jni.example.p2pinvest.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import jni.example.base.BaseCustomView;
import jni.example.common.Constant_TopBar;
import jni.example.p2pinvest.R;

public class Top_Bar extends BaseCustomView {
    private TextView topBarText;
    private ImageView back;
    private ImageView setting;

    public Top_Bar(Context context) {
        super(context);
    }

    public Top_Bar(Context context, @Nullable AttributeSet attrs) {
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
    }

    //TODO 设置fragment切换后修改topBar的内容
    public void setTopBarText(int index) {
        switch (index){
            case 0:
                conceal_image();
                topBarText.setText(Constant_TopBar.HOME);
                break;
            case 1:
                conceal_image();
                topBarText.setText(Constant_TopBar.INVESTMENT);
                break;
            case 2:
                conceal_image();
                setting.setVisibility(VISIBLE);
                topBarText.setText(Constant_TopBar.MYASSETS);
                break;
            case 3:
                conceal_image();
                topBarText.setText(Constant_TopBar.MORE);
                break;
        }
    }


    public void conceal_image(){
        setting.setVisibility(GONE);
        back.setVisibility(GONE);
    }
}
