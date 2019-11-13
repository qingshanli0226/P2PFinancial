package com.bwei.p2p.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLayout extends ViewGroup {

    private Random mRdm;
    private int mXRegularity;
    private int mYRegularity;
    private int mAreaCount;
    private int[][] mAreaDensity;
    private Set<View> mFixedViews;
    private Adapter mAdapter;
    private List<View> mRecycledViews;
    private boolean mLayouted;
    private int mOverlapAdd = 2;
    public RandomLayout(Context context) {
        super(context);
        init();
    }
//    初始化
    private void init() {
        mLayouted = false;
        mRdm = new Random();
        setRegularity(1, 1);
        mFixedViews = new HashSet<View>();
        mRecycledViews = new LinkedList<View>();
    }

    public boolean hasLayouted() {
        return mLayouted;
    }

    public void setRegularity(int xRegularity, int yRegularity) {
        if (xRegularity > 1) {
            this.mXRegularity = xRegularity;
        } else {
            this.mXRegularity = 1;
        }
        if (yRegularity > 1) {
            this.mYRegularity = yRegularity;
        } else {
            this.mYRegularity = 1;
        }
        this.mAreaCount = mXRegularity * mYRegularity;
        this.mAreaDensity = new int[mYRegularity][mXRegularity];
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
    }

    private void resetAllAreas() {
        mFixedViews.clear();
        for (int i = 0; i < mYRegularity; i++) {
            for (int j = 0; j < mXRegularity; j++) {
                mAreaDensity[i][j] = 0;
            }
        }
    }
    private void pushRecycler(View scrapView) {
        if (null != scrapView) {
            mRecycledViews.add(0, scrapView);
        }
    }
    private View popRecycler() {
        final int size = mRecycledViews.size();
        if (size > 0) {
            return mRecycledViews.remove(0);
        } else {
            return null;
        }
    }
    private void generateChildren() {
        if (null == mAdapter) {
            return;
        }
        final int childCount = super.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            pushRecycler(super.getChildAt(i));
        }
        super.removeAllViewsInLayout();
        final int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View convertView = popRecycler();
            View newChild = mAdapter.getView(i, convertView);
            if (newChild != convertView) {
                pushRecycler(convertView);
            }
            super.addView(newChild, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }

    public void redistribute() {
        resetAllAreas();
        requestLayout();
    }
    public void refresh() {
        resetAllAreas();
        generateChildren();
        requestLayout();
    }
    @Override
    public void removeAllViews() {
        super.removeAllViews();
        resetAllAreas();
    }
    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int thisW = r - l - this.getPaddingLeft() - this.getPaddingRight();
        int thisH = b - t - this.getPaddingTop() - this.getPaddingBottom();
        int contentRight = r - getPaddingRight();
        int contentBottom = b - getPaddingBottom();
        List<Integer> availAreas = new ArrayList<Integer>(mAreaCount);
        for (int i = 0; i < mAreaCount; i++) {
            availAreas.add(i);
        }

        int areaCapacity = (count + 1) / mAreaCount + 1;
        int availAreaCount = mAreaCount;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            if (!mFixedViews.contains(child)) {
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), MeasureSpec.AT_MOST);//为子View准备测量的参数
                int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), MeasureSpec.AT_MOST);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                int childW = child.getMeasuredWidth();
                int childH = child.getMeasuredHeight();
                float colW = thisW / (float) mXRegularity;
                float rowH = thisH / (float) mYRegularity;

                while (availAreaCount > 0) {
                    int arrayIdx = mRdm.nextInt(availAreaCount);
                    int areaIdx = availAreas.get(arrayIdx);
                    int col = areaIdx % mXRegularity;
                    int row = areaIdx / mXRegularity;
                    if (mAreaDensity[row][col] < areaCapacity) {
                        int xOffset = (int) colW - childW;
                        if (xOffset <= 0) {
                            xOffset = 1;
                        }
                        int yOffset = (int) rowH - childH;
                        if (yOffset <= 0) {
                            yOffset = 1;
                        }
                        params.mLeft = getPaddingLeft() + (int) (colW * col + mRdm.nextInt(xOffset));
                        int rightEdge = contentRight - childW;
                        if (params.mLeft > rightEdge) {
                            params.mLeft = rightEdge;
                        }
                        params.mRight = params.mLeft + childW;

                        params.mTop = getPaddingTop() + (int) (rowH * row + mRdm.nextInt(yOffset));
                        int bottomEdge = contentBottom - childH;
                        if (params.mTop > bottomEdge) {
                            params.mTop = bottomEdge;
                        }
                        params.mBottom = params.mTop + childH;

                        if (!isOverlap(params)) {
                            mAreaDensity[row][col]++;
                            child.layout(params.mLeft, params.mTop, params.mRight, params.mBottom);
                            mFixedViews.add(child);
                            break;
                        } else {
                            availAreas.remove(arrayIdx);
                            availAreaCount--;
                        }
                    } else {
                        availAreas.remove(arrayIdx);
                        availAreaCount--;
                    }
                }
            }
        }
        mLayouted = true;
    }
    private boolean isOverlap(LayoutParams params) {
        int l = params.mLeft - mOverlapAdd;
        int t = params.mTop - mOverlapAdd;
        int r = params.mRight + mOverlapAdd;
        int b = params.mBottom + mOverlapAdd;

        Rect rect = new Rect();

        for (View v : mFixedViews) {
            int vl = v.getLeft() - mOverlapAdd;
            int vt = v.getTop() - mOverlapAdd;
            int vr = v.getRight() + mOverlapAdd;
            int vb = v.getBottom() + mOverlapAdd;
            rect.left = Math.max(l, vl);
            rect.top = Math.max(t, vt);
            rect.right = Math.min(r, vr);
            rect.bottom = Math.min(b, vb);
            if (rect.right >= rect.left && rect.bottom >= rect.top) {
                return true;
            }
        }
        return false;
    }
    public static interface Adapter {

        public abstract int getCount();

        public abstract View getView(int position, View convertView);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        private int mLeft;
        private int mRight;
        private int mTop;
        private int mBottom;

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }
    }
    }