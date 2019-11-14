package com.bw.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
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

    private final IcsLinearLayout mTabLayout;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    public TabPageIndicator(Context context, IcsLinearLayout mTabLayout) {
        super(context);
        this.mTabLayout = mTabLayout;
    }

    public TabPageIndicator(Context context, AttributeSet attrs, IcsLinearLayout mTabLayout) {
        super(context, attrs);
        this.mTabLayout = mTabLayout;

        setHorizontalScrollBarEnabled(false);

        mTabLayout = new IcsLinearLayout(context,R.attr.vpiTabPageIndicatorStyle);
        addView(mTabLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener){
        mTabReselectedListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)){
            if (childCount > 2){
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            }else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        }else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth){
            setCurrentItem(mSelectedTabIndex);
        }

    }

    private void animateToTab(int position){
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null){
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            @Override
            public void run() {
                int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth());
                smoothScrollTo(scrollPos,0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mTabSelector != null){
            removeCallbacks(mTabSelector);
        }

    }

    private void addTab(int index,CharSequence text,int iconResId){
        TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);

        if (iconResId != 0){
            tabView.setCompoundDrawablesWithIntrinsicBounds(iconResId,0,0,0);
        }

        mTabLayout.addView(tabView,new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));

    }

    @Override
    public void setViewPage(ViewPager view) {
        if (mViewPager == view){
            return;
        }
        if (mViewPager != null){
            mViewPager.setOnPageChangeListener(null);
        }
        PagerAdapter adapter = view.getAdapter();
        if (adapter == null){
            throw new IllegalStateException("Viewpager does not have adapter instance.");
        }

        mViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPage(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        if (mViewPager == null){
            throw new IllegalStateException("Viewpager has not been bound.");
        }
        mSelectedTabIndex = item;
        mViewPager.setCurrentItem(item);

        int tabCount  = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            View child = mTabLayout.getChildAt(i);
            boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected){
                animateToTab(item);
            }
        }
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        IconPagerAdapter iconAdapter = null;
        if (adapter instanceof IconPagerAdapter){
            iconAdapter = (IconPagerAdapter) adapter;
        }
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null){
                title = EMPTY_TITLE;
            }
            int iconResId = 0;
            if (iconAdapter != null){
                iconResId = iconAdapter.getIconResId(i);
            }
            addTab(i,title,iconResId);
        }
        if (mSelectedTabIndex > count){
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        if (mListener != null){
            mListener.onPageScrolled(i,v,i1);
        }
    }

    @Override
    public void onPageSelected(int i) {
        if (mListener != null){
            mListener.onPageSelected(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (mListener != null){
            mListener.onPageScrollStateChanged(i);
        }
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
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth,MeasureSpec.EXACTLY),heightMeasureSpec);
            }

        }

        public int getIndex() {
            return mIndex;
        }
    }
}
