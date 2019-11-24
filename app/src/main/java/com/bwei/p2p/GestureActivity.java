package com.bwei.p2p;

import android.widget.TextView;

import com.bwei.base.BaseActivity;
import com.bwei.base.UserManager;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GestureActivity extends BaseActivity {

private PatternLockerView patternLocker;
private PatternIndicatorView patternIndicator;
private GestureHelper gestureHelper;
private TextView tv;
    @Override
    protected void initView() {
        patternLocker=findViewById(R.id.gesture_lock_view);
        patternIndicator=findViewById(R.id.gesture_indicator_view);
        tv=findViewById(R.id.gesture_tv);
        gestureHelper=new GestureHelper(this);
    }

    @Override
    protected void initDate() {
        patternLocker.clearHitState();
        patternLocker.setEnableHapticFeedback(true);
        tv.setText("设置解图案");
        patternLocker.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {
            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                if (UserManager.getInstance().readIsGesture()){
                    boolean isok=isPatternOk(list);
                    patternLockerView.updateStatus(!isok);
                    patternIndicator.updateState(list,isok);
                }else{
                    gestureHelper.loginVallidata(list);
                    UserManager.getInstance().setonBackIsGesture(false);
                    if (gestureHelper.isOk()&&gestureHelper.isFinish()){
//                        输入正确
                        setResult(101);
                    }else{
                        setResult(102);
                    }
                    finish();
                }
                updateMessenger();
            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {
                if (gestureHelper.isFinish()){
                    finish();
                }
            }
        });
    }
//更新消息信息
    private void updateMessenger() {
        tv.setText(gestureHelper.getMessage());
    }

    private boolean isPatternOk(List<Integer> list) {
        gestureHelper.validateForSetting(list);
        return gestureHelper.isOk();
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
