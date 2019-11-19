package com.bw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            measureChild(childView,widthMeasureSpec,heightMeasureSpec);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            if (lineWidth + childWidth + params.leftMargin + params.rightMargin <= widthSize){
                lineWidth += childWidth + params.leftMargin + params.rightMargin;
                lineHeight = Math.max(lineHeight,childHeight + params.topMargin + params.bottomMargin);
            }else {
                width = Math.max(width,lineWidth);
                height += lineHeight;

                lineWidth = childWidth + params.leftMargin + params.rightMargin;
                lineHeight = childHeight + params.topMargin + params.bottomMargin;

            }

            if (i == childCount - 1){
                width = Math.max(width,lineWidth);
                height += lineHeight;
            }

        }

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width,(heightMode == MeasureSpec.EXACTLY) ? heightSize : height);

    }

    private List<List<View>> allViews = new ArrayList<>();
    private List<Integer> allHeights = new ArrayList<>();

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineList = new ArrayList<>();

        int width = this.getMeasuredWidth();
        int childCount = getChildCount();
        for (int j = 0; j < childCount; j++) {
            View childView = getChildAt(j);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            if (lineWidth + childWidth + params.leftMargin + params.rightMargin <= width){
                lineList.add(childView);
                lineWidth += childWidth + params.leftMargin + params.rightMargin;
                lineHeight = Math.max(lineHeight,childHeight + params.topMargin + params.bottomMargin);
            }else {
                allViews.add(lineList);
                allHeights.add(lineHeight);

                lineWidth = childWidth + params.leftMargin + params.rightMargin;
                lineHeight = childHeight + params.topMargin + params.bottomMargin;
                lineList = new ArrayList<>();
                lineList.add(childView);

            }

            if (j == childCount - 1){
                allViews.add(lineList);
                allHeights.add(lineHeight);
            }

        }
    }
}
