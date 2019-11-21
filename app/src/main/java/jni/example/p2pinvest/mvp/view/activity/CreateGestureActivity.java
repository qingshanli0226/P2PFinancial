package jni.example.p2pinvest.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.star.lockpattern.util.LockPatternUtil;
import com.star.lockpattern.widget.LockPatternIndicator;
import com.star.lockpattern.widget.LockPatternView;

import java.util.ArrayList;
import java.util.List;

import jni.example.base.BaseActivity;
import jni.example.common.Constant;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.util.cache.ACache;
public class CreateGestureActivity extends BaseActivity {

    private LockPatternIndicator lockPatterIndicator;
    private TextView messageTv;
    private LockPatternView lockPatternView;
    private Button resetBtn;
    private ACache aCache;
    private List<LockPatternView.Cell> mChosenPattern = null;
    private static final long DELAYTIME = 600L;
    private static final String TAG = "CreateGestureActivity";

    @Override
    public int layoutId() {
        return R.layout.activity_create_gesture;
    }

    @Override
    public void init() {
        lockPatterIndicator = (LockPatternIndicator) findViewById(R.id.lockPatterIndicator);
        messageTv = (TextView) findViewById(R.id.messageTv);
        lockPatternView = (LockPatternView) findViewById(R.id.lockPatternView);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        aCache = ACache.get(CreateGestureActivity.this);
        lockPatternView.setOnPatternListener(patternListener);
    }

    @Override
    public void initData() {
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChosenPattern = null;
                lockPatterIndicator.setDefaultIndicator();
                updateStatus(Status.DEFAULT, null);
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
            }
        });
    }

    /**
     * 手势监听
     */
    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
            //updateStatus(Status.DEFAULT, null);
            lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            //Log.e(TAG, "--onPatternDetected--");
            if(mChosenPattern == null && pattern.size() >= 4) {
                mChosenPattern = new ArrayList<LockPatternView.Cell>(pattern);
                updateStatus(Status.CORRECT, pattern);
            } else if (mChosenPattern == null && pattern.size() < 4) {
                updateStatus(Status.LESSERROR, pattern);
            } else if (mChosenPattern != null) {
                if (mChosenPattern.equals(pattern)) {
                    updateStatus(Status.CONFIRMCORRECT, pattern);
                } else {
                    updateStatus(Status.CONFIRMERROR, pattern);
                }
            }
        }
    };

    /**
     * 更新状态
     * @param status
     * @param pattern
     */
    private void updateStatus(Status status, List<LockPatternView.Cell> pattern) {
        messageTv.setTextColor(getResources().getColor(status.colorId));
        messageTv.setText(status.strId);
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case CORRECT:
                updateLockPatternIndicator();
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case LESSERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case CONFIRMERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockPatternView.postClearPatternRunnable(DELAYTIME);
                break;
            case CONFIRMCORRECT:
                saveChosenPattern(pattern);
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                setLockPatternSuccess();
                break;
        }
    }

    /**
     * 更新 Indicator
     */
    private void updateLockPatternIndicator() {
        if (mChosenPattern == null)
            return;
        lockPatterIndicator.setIndicator(mChosenPattern);
    }

    /**
     * 成功设置了手势密码(跳到首页)
     */
    private void setLockPatternSuccess() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    /**
     * 保存手势密码
     */
    private void saveChosenPattern(List<LockPatternView.Cell> cells) {
        byte[] bytes = LockPatternUtil.patternToHash(cells);
        aCache.put(Constant.GESTURE_PASSWORD, bytes);
    }

    private enum Status {
        //默认的状态，刚开始的时候（初始化状态）
        DEFAULT(R.string.create_gesture_default, R.color.grey_a5a5a5),
        //第一次记录成功
        CORRECT(R.string.create_gesture_correct, R.color.grey_a5a5a5),
        //连接的点数小于4（二次确认的时候就不再提示连接的点数小于4，而是提示确认错误）
        LESSERROR(R.string.create_gesture_less_error, R.color.red_f4333c),
        //二次确认错误
        CONFIRMERROR(R.string.create_gesture_confirm_error, R.color.red_f4333c),
        //二次确认正确
        CONFIRMCORRECT(R.string.create_gesture_confirm_correct, R.color.grey_a5a5a5);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }
        private int strId;
        private int colorId;
    }
}
