package com.bw.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {

    private static final CharSequence EMPTY_TITLE = "";

    public interface OnTabReselectedListener{
        void onTabReselected(int position);
    }

    private Runnable mTabSelector;

    private final OnClickListener mTabClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            TabView tabView = (TabView) view;
            int oldSelected = mViewPager.getCurrentItem();
            int newSelected = tabView.getIndex();
            mViewPager.setCurrentItem(newSelected);
            if (oldSelected == newSelected && mTabReselectedListener != null){
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

//    private final

    private ViewPager mViewPager;

    private int mMaxTabWidth;

    private OnTabReselectedListener mTabReselectedListener;

    public TabPageIndicator(Context context) {
        super(context);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setViewPage(ViewPager view) {

    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {

    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

    }

    @Override
    public void notifyDataSetChanged() {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @SuppressLint("AppCompatCustomView")
    private class TabView extends TextView {

        private int mIndex;

        public TabView(Context context) {
            super(context,null,R.attr.vpiTabPageIndicatorStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth){

            }

        }

        public int getIndex() {
            return mIndex;
        }
    }
}
