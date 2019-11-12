package com.bw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

public class IcsLinearLayout extends LinearLayout {

    private static final int[] LL = new int[]{
            android.R.attr.divider,
            android.R.attr.showDividers,
            android.R.attr.dividerPadding,
    };

    private static final int LL_DIVIDER = 0;
    private static final int LL_SHOW_DIVIDERS = 1;
    private static final int LL_DIVIDER_PADDING = 2;

    private Drawable mDrawable;
    private int mDividerWidth;
    private int mDividerHeight;
    private int mShowDivider;
    private int mDividerPadding;

    public IcsLinearLayout(Context context,int themeAttr) {
        super(context);

        TypedArray a = context.obtainStyledAttributes(null, LL, themeAttr, 0);
        setDividerDrawable(a.getDrawable(IcsLinearLayout.LL_DIVIDER));
        mDividerPadding = a.getDimensionPixelSize(LL_DIVIDER_PADDING,0);
        mShowDivider = a.getInteger(LL_SHOW_DIVIDERS,SHOW_DIVIDER_NONE);
        a.recycle();

    }

    @Override
    public void setDividerDrawable(Drawable divider) {
//        super.setDividerDrawable(divider);

        if (divider == mDrawable){
            return;
        }
        mDrawable = divider;
        if (divider != null){
            mDividerWidth = divider.getIntrinsicWidth();
            mDividerHeight = divider.getIntrinsicHeight();
        }else {
            mDividerWidth = 0;
            mDividerHeight = 0;
        }
        setWillNotDraw(divider == null);
        requestLayout();

    }
}
