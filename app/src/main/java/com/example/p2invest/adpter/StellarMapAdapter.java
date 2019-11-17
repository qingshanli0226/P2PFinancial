package com.example.p2invest.adpter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.leon.stellarmap.lib.StellarMap;

import java.util.List;
import java.util.Random;

public class StellarMapAdapter implements StellarMap.Adapter {
    private static final int PAGE_SIZE = 15;

    private Context mContext;
    private List<String> mDataList;

    public StellarMapAdapter(Context mContext, List<String> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    // 返回组(页面)的个数

    @Override
    public int getGroupCount() {
        int pageCount = mDataList.size() / PAGE_SIZE;
        if (mDataList.size() % PAGE_SIZE != 0) {//有余数的时候
            pageCount++;
        }
        return pageCount;
    }

    /**
     * 返回对应组(页面)条目的个数
     *
     * @param i
     * @return
     */
    @Override
    public int getCount(int i) {
        if (mDataList.size() % PAGE_SIZE != 0) { //有余数
            if (i == getGroupCount() - 1) { //最后一组
                return mDataList.size() % PAGE_SIZE;
            }
        }
        return PAGE_SIZE;
    }

    /**
     * 返回 对应组中对应位置的view
     *
     * @param group
     * @param position
     * @param convertView
     * @return
     */
    @Override
    public View getView(int group, int position, View convertView) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(mContext);
        } else {
            tv = (TextView) convertView;
        }

        int index = group * PAGE_SIZE + position;
        String data = mDataList.get(index);
        tv.setText(data);

        //随机大小
        Random random = new Random();
        tv.setTextSize(random.nextInt(2) + 16); //12-18

        //随机颜色
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        tv.setTextColor(ranColor);

        return tv;
    }

    /**
     * 返回放大 或者缩小下一组的下标
     * @param group 当前组的下标
     * @param isZoomIn true 表示放大, false 表示缩小
     * @return
     */
    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        if (isZoomIn){
            return (group+1) % getGroupCount();
        }else {
            return (group-1+getGroupCount()) % getGroupCount();
        }
    }
}
