package com.bwei.p2p;

import com.bwei.base.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class GestureActivity extends BaseActivity {

private PatternLockerView patternLocker;
private PatternIndicatorView patternIndicator;
    @Override
    protected void initView() {
        patternLocker=findViewById(R.id.gesture_lock_view);
        patternIndicator=findViewById(R.id.gesture_indicator_view);
    }

    @Override
    protected void initDate() {
        patternLocker.clearHitState();
        patternLocker.setEnableHapticFeedback(true);
        patternIndicator.updateState(Arrays.asList(1,2),false);
        patternLocker.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture;
    }
    @Override
    public void onConnected() {
           }

    @Override
    public void onDisConnected() {
         }
}
