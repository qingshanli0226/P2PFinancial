package com.bw.view;

import android.support.v4.view.ViewPager;

public interface PageIndicator extends ViewPager.OnPageChangeListener {

    void setViewPage(ViewPager view);

    void setViewPager(ViewPager view,int initialPosition);

    void setCurrentItem(int item);

    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

    void notifyDataSetChanged();

}
