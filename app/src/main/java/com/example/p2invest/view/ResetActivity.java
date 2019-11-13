package com.example.p2invest.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.p2invest.R;
import com.github.ihsg.patternlocker.DefaultIndicatorNormalCellView;
import com.github.ihsg.patternlocker.INormalCellView;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.jcodecraeer.xrecyclerview.progressindicator.indicator.BallScaleRippleMultipleIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResetActivity extends BaseActivity {
    private TextView tvTitle;
    private ImageView ivtitlesetting;
    private View titleView;
    private LinearLayout titleLine;
    private PatternIndicatorView patternindicatorview;
    private PatternLockerView patternlockview;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initdata() {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_reset;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle=findViewById(R.id.tvtitle);
        ivtitlesetting=findViewById(R.id.ivtitlesetting);
        titleView=findViewById(R.id.titleView);
        titleLine=findViewById(R.id.titleLine);
        patternindicatorview=findViewById(R.id.patternindicatorview);
        patternlockview=findViewById(R.id.patternlockview);

        ivtitlesetting.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        titleLine.setBackgroundColor(Color.BLACK);
        tvTitle.setText("设置手势密码");
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(20);

       // DefaultIndicatorNormalCellView view = (DefaultIndicatorNormalCellView) patternlockview.getNormalCellView();

        patternlockview.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                patternindicatorview.updateState(list,true);
            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {

            }
        });
    }
}
