package com.example.p2pdemo.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFlowLayout extends ViewGroup {

    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int width=0;
        int height=0;

        int lineWidth=0;
        int lineHeight=0;

        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);

            int childHieght = childAt.getMeasuredHeight();
            int childWidth = childAt.getMeasuredWidth();

            MarginLayoutParams mp= (MarginLayoutParams) childAt.getLayoutParams();
            if(lineWidth+childWidth+mp.leftMargin+mp.rightMargin<=widthSize){
                lineWidth+=childWidth+mp.leftMargin+mp.rightMargin;
                lineHeight=Math.max(lineHeight,childHieght+mp.topMargin+mp.bottomMargin);
            }else{

                //换行
                width=Math.max(width,lineHeight);
                height+=lineHeight;
                //重置
                lineWidth=childWidth+mp.leftMargin+mp.rightMargin;
                lineHeight=childHieght+mp.topMargin+mp.bottomMargin;

            }
            if(i==childCount-1){
                width=Math.max(width,lineWidth);
                height+=lineHeight;
            }

        }

            setMeasuredDimension((widthMode==MeasureSpec.EXACTLY)?widthSize : width,(heightMode==MeasureSpec.EXACTLY)?heightSize:height);



    }
   private List<List<View>> allViews=new ArrayList<>();
    private List<Integer> allHeights=new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //一、给两个集合添加元素。

        //每一行的宽高值
        int lineWidth = 0;
        int lineHeight = 0;

        //提供一个集合，保存一行childView
        List<View> lineList = new ArrayList<>();
        //获取布局的宽度
        int width = this.getMeasuredWidth();

        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //获取视图的测量宽高、边距
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

            if(i == childCount - 1){//如果是最后一个元素
                allViews.add(lineList);
                allHeights.add(lineHeight);
            }

        }

        Log.e("TAG", "allViews.size = " + allViews.size() + ",allHeights.size = " + allHeights.size());

        //二、给每一个子视图指定显示的位置
        int x = 0;
        int y = 0;
        for(int i = 0; i < allViews.size(); i++) {//每遍历一次，对应一行元素
            List<View> lineViews = allViews.get(i);//取出当前行构成的集合
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();

                MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();

                int left = x + mp.leftMargin;
                int top = y + mp.topMargin;
                int right = left + childWidth;
                int bottom = top + childHeight;

                childView.layout(left, top, right, bottom);

                x += childWidth + mp.leftMargin + mp.rightMargin;

            }
            y += allHeights.get(i);
            x = 0;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;    }
}
