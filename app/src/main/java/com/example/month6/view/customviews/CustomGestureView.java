package com.example.month6.view.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.month6.R;

import java.util.ArrayList;

public class CustomGestureView extends RelativeLayout {
    ArrayList<Boolean> list = new ArrayList<>();
    ArrayList<Integer> nums = new ArrayList<>();
    ArrayList<ImageView> imgs = new ArrayList<>();
    float now_x;
    float now_y;
    boolean flag = false;

    public CustomGestureView(Context context) {
        this(context, null);
    }

    public CustomGestureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_gesture, null);
        imgs.add(inflate.findViewById(R.id.img1));
        imgs.add(inflate.findViewById(R.id.img2));
        imgs.add(inflate.findViewById(R.id.img3));
        imgs.add(inflate.findViewById(R.id.img4));
        imgs.add(inflate.findViewById(R.id.img5));
        imgs.add(inflate.findViewById(R.id.img6));
        imgs.add(inflate.findViewById(R.id.img7));
        imgs.add(inflate.findViewById(R.id.img8));
        imgs.add(inflate.findViewById(R.id.img9));

        for (int i = 0; i < 10; i++) {
            list.add(false);
        }

        this.addView(inflate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (flag) {
            return false;
        } else {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    now_x = event.getX();
                    now_y = event.getY();
                    for (int i = 0; i < imgs.size(); i++) {
                        if (now_x >= imgs.get(i).getLeft() && now_x <= imgs.get(i).getRight()) {
                            if (now_y >= imgs.get(i).getTop() && now_y <= imgs.get(i).getBottom()) {
                                //获取到触碰到的控件的下标
                                if (!list.get(i)) {
                                    nums.add(i + 1);
                                }
                                imgs.get(i).setImageResource(R.mipmap.i2);
                                list.set(i, true);
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    for (int i = 0; i < list.size(); i++) {
                        list.set(i, false);
                    }
                    if (nums.size() < 4) {
                        nums.clear();
                        for (int i = 0; i < imgs.size(); i++) {
                            imgs.get(i).setImageResource(R.mipmap.img);
                        }
                    } else {
                        Log.e("xxxx", "手势密码" + nums);
                        flag = true;
                    }
                    break;
            }
            return true;
        }
    }

    public ArrayList<Integer> getNums() {
        if (flag) {
            return nums;
        } else {
            return null;
        }
    }
}
