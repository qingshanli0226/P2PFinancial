package com.bw.jinrong.controller.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.bw.jinrong.controller.utils.UIUtils;

public class MyScrollView extends ScrollView {

    private View childView;
    private UIUtils uiUtils = new UIUtils();

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //获取子视图
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() > 0){
            childView = getChildAt(0);
        }

    }

    //上一次y轴方向操作的坐标位置
    private int lastY;
    //用于记录临界状态的左，上，右，下
    private Rect normal = new Rect();
    //是否动画结束
    private boolean isFinishAnimation = true;

    private int lastX,downX,downY;

    //拦截：实现父视图对子视图的拦截
    //是否拦截成功，取决于方法的返回值。返回值true：拦截成功。反之，拦截失败
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean isIntercept = false;
        int eventX = (int) ev.getX();
        int eventY = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = downX = eventX;
                lastY = downY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                //获取水平和垂直方向的移动距离
                int absX = Math.abs(eventX - downX);
                int absY = Math.abs(eventY - downY);

                if (absY > absX && absY >= uiUtils.dp2px(10)){
                    //执行拦截
                    isIntercept = true;
                }

                lastX = eventX;
                lastY = eventY;

                break;
        }

        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (childView == null || isFinishAnimation){
            return super.onTouchEvent(ev);
        }

        //获取当前的y轴坐标
        int eventY = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                //微小的移动量
                int dy = eventY - lastY;

                if (isNeedMove()){
                    if (normal.isEmpty()){
                        //记录了childView临界状态的左，上，右，下
                        normal.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
                    }

                    //重新布局
                    childView.layout(childView.getLeft(),childView.getTop() + dy / 2,childView.getRight(),childView.getBottom() + dy / 2);
                }

                //重新赋值
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()){
                    //使用平移动画
                    int translateY = childView.getBottom() - normal.bottom;
                    TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-translateY);
                    translateAnimation.setDuration(2000);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isFinishAnimation = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isFinishAnimation = true;
                            //清除动画
                            childView.clearAnimation();
                            //重新布局
                            childView.layout(normal.left,normal.top,normal.right,normal.bottom);
                            //清除normal的数据
                            normal.setEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    //启动动画
                    childView.startAnimation(translateAnimation);
                }
                break;
        }

        return super.onTouchEvent(ev);

    }

    //判断是否需要执行平移动画
    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    private boolean isNeedMove() {
        //获取子视图的高度
        int childMeasuredHeight = childView.getMeasuredHeight();
        //获取布局的高度
        int scrollViewMeasuredHeight = this.getMeasuredHeight();

        //dy >= 0
        int dy = childMeasuredHeight - scrollViewMeasuredHeight - scrollViewMeasuredHeight;

        //获取用户在y轴方向上的偏移量（上 + 下 -）
        int scrollY = this.getScrollY();

        if (scrollY <= 0 || scrollY >= dy){
            //按照我们自定义的MyScrollView的方式处理
            return true;
        }

        //其它处在临界范围内的，返回false。即表示，仍按照Scrollview的处理
        return false;
    }
}
