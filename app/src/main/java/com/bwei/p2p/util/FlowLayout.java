package com.bwei.p2p.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    private List<List<View>> allViews = new ArrayList<>();//每一行的子视图的集合构成的集合。
    private List<Integer> allHeights = new ArrayList<>();//每一行的高度构成的集合。
    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width=0;
        int height=0;

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);

            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
            int childWidth = childAt.getMeasuredWidth();
            int childHeight = childAt.getMeasuredHeight();
            MarginLayoutParams mp = (MarginLayoutParams) childAt.getLayoutParams();

            if (lineWidth+childWidth+mp.leftMargin+mp.rightMargin<=widthSize){
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight,childHeight + mp.topMargin + mp.bottomMargin);

            }else{
//                换行
                width = Math.max(width,lineWidth);
                height += lineHeight;

//                重置
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
            }
        if (i==childCount-1){
            width=Math.max(width,lineWidth);
            height+=lineHeight;
        }
        }
        setMeasuredDimension((widthMode==MeasureSpec.EXACTLY)?widthSize : width,(heightMode == MeasureSpec.EXACTLY) ? heightSize : height);



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineList = new ArrayList<>();
        int width = this.getMeasuredWidth();

        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();

            if(lineWidth + childWidth + mp.leftMargin + mp.rightMargin <= width){//不换行
                lineList.add(childView);
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight,childHeight + mp.topMargin + mp.bottomMargin);

            }else{//换行
                allViews.add(lineList);
                allHeights.add(lineHeight);

                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
                lineList = new ArrayList<>();
                lineList.add(childView);
            }

            if(i == childCount - 1){
                allViews.add(lineList);
                allHeights.add(lineHeight);
            }

        }

        int x = 0;
        int y = 0;
        for(int i = 0; i < allViews.size(); i++) {
            List<View> lineViews = allViews.get(i);
            for(int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();

                MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();

                int left = x + mp.leftMargin;
                int top = y + mp.topMargin;
                int right = left + childWidth;
                int bottom = top + childHeight;

                childView.layout(left,top,right,bottom);

                x +=  childWidth + mp.leftMargin + mp.rightMargin;

            }
            y += allHeights.get(i);
            x = 0;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }
}
