package com.example.p2invest.custor;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private View view;
    private int mLastY;
    private Rect mRect=new Rect();
    private boolean isFinishAnimation=true;

    public MyScrollView(Context context) {
        super(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount()>0){
            view=getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (view==null ||!isFinishAnimation){
            return super.onTouchEvent(ev);
        }
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                int di = y - mLastY;
                if (isNeedMove()){
                    if (mRect.isEmpty()){
                        mRect.set(view.getLeft(),view.getTop(),view.getRight(),view.getBottom());
                    }
                    view.layout(view.getLeft(),view.getTop()+di/2,view.getRight(),view.getBottom()+di/2);
                }
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:
                int translateY = view.getBottom() - mRect.bottom;
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -translateY);
                animation.setDuration(200);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isFinishAnimation=false;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                          isFinishAnimation=true;
                          view.clearAnimation();
                          view.layout(mRect.left,mRect.top,mRect.right,mRect.bottom);
                          mRect.setEmpty();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(animation);
                break;
        }

        return super.onTouchEvent(ev);
    }
    private boolean isNeedMove(){
        int measuredHeight = view.getMeasuredHeight();
        int scrolldHeight1 = this.getMeasuredHeight();

        int i = measuredHeight - scrolldHeight1;
        int y = (int) this.getScaleY();
        if (y<=0 || y>=i){
            return  true;
        }


        return  false;
    }
}
