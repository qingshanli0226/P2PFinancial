package com.example.p2invest.custor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class MyFlowLayout extends ViewGroup {
    private MarginLayoutParams marginLayoutParams;
    private int childwidth;

    int childheight;

    int factwidth;

    int factheight;

    int linewidth;
    /**
     * 每行高度
     */
    int lineheight;
    /**
     * 用于记录上一次的行高
     */
    int lastchildheight = 0;

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyFlowLayout(Context context) {
        super(context, null);
    }

    /**
     * 根据子view来确定宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //添加了子视图后，父级视图布局会发生改变，onMeasure方法会被执行两遍
        factheight = 0;
        factwidth = 0;
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int childcount = getChildCount();
        for (int i = 0; i < childcount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            childwidth = child.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            childheight = child.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            //如果child的宽度和加起来大于了父级宽度，则计算最大值，并作为父级实际宽度
            if (linewidth + childwidth > widthsize) {
                //计算父级宽度
                factwidth = Math.max(widthsize, linewidth);
                //计算父级高度
                factheight += lastchildheight;
                //重置行宽
                linewidth = 0;
                //重置行高
                lastchildheight = 0;
                lineheight = 0;
            }
            linewidth += childwidth;
            //取最大行高(用于应对特殊字体大小)
            lineheight = Math.max(lastchildheight, childheight);
            lastchildheight = lineheight;
            //特殊情况，处理最后一个child（有可能该行只有一个view,也可能最后一个view刚好处于最后一行最后位置）
            if (i == childcount - 1) {
                factwidth = Math.max(widthsize, linewidth);
                factheight += lastchildheight;
                lastchildheight = 0;
                lineheight = 0;
                linewidth = 0;
            }
        }
        //把测量结果设置为父级宽高
        setMeasuredDimension(widthmode == MeasureSpec.EXACTLY ? widthsize : factwidth,
                heightmode == MeasureSpec.EXACTLY ? heightsize : factheight);
    }

    /**
     * 每行view的集合
     */
    private List<List<View>> AllChildView = new ArrayList<>();
    /**
     * 每行高度的集合
     */
    private List<Integer> LineHeight = new ArrayList<Integer>();

    /**
     * 排版 (横向排列)
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        factwidth = getMeasuredWidth();
        AllChildView.clear();
        LineHeight.clear();
        List<View> linelist = new ArrayList<View>();
        //计算每行可以放view的个数，并放进集合
        int childcount = getChildCount();
        for (int i = 0; i < childcount; i++) {
            View view = getChildAt(i);
            marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            int childwidth = view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int childheight = view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            //每行子view加起来的宽度大于父级宽度 就把该行子view集合放进所有行的集合里
            if (linewidth + childwidth >= factwidth) {
                LineHeight.add(lastchildheight);//行高集合
                AllChildView.add(linelist);//行数集合
                //重置
                linewidth = 0;
                lastchildheight = 0;
                //重新创建一个集合
                linelist = new ArrayList<View>();
            }
            //取每行的最大高度
            lineheight = Math.max(childheight, lastchildheight);
            lastchildheight = lineheight;
            linewidth += childwidth;
            //每行的view集合
            linelist.add(view);
            //如果最后一行没有大于父级宽度，需要特殊处理
            if (i == childcount - 1) {
                LineHeight.add(lastchildheight);//行高集合
                AllChildView.add(linelist);//行数集合
                lastchildheight = 0;
                linewidth = 0;
            }
        }
        int left = 0;
        int top = 0;
        //设置子view的位置
        for (int w = 0; w < AllChildView.size(); w++) {//总共多少行
            linelist = AllChildView.get(w);
            lineheight = LineHeight.get(w);
            for (int m = 0; m < linelist.size(); m++) {//每行排版
                View childview = linelist.get(m);
                //隐藏状态的子view不参与排版
                if (childview.getVisibility() == View.GONE) {
                    continue;
                }
                marginLayoutParams = (MarginLayoutParams) childview.getLayoutParams();
                int cleft = left + marginLayoutParams.leftMargin;
                int ctop = top + marginLayoutParams.topMargin + (lineheight / 2 - childview.getHeight() / 2);
                int cright = cleft + childview.getMeasuredWidth();
                int cbottom = ctop + childview.getMeasuredHeight();
                childview.layout(cleft, ctop, cright, cbottom);
                left += childview.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
            //每行排完之后重新设置属性
            left = 0;
            top += lineheight;
        }
    }
//  @Override
//  public LayoutParams generateLayoutParams(AttributeSet attrs) {
//      return new MarginLayoutParams(getContext(), attrs);
//  }
}