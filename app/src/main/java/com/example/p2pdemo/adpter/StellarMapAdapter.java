package com.example.p2pdemo.adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.leon.stellarmap.lib.StellarMap;

import java.util.Random;
//
public class StellarMapAdapter implements StellarMap.Adapter {
    private static final int PAGE_SIZE = 10;
    private String[] strings;
    private Context mContext;
    private onItemOnClickListener onItemOnClickListener;
    public StellarMapAdapter(String[] strings, Context mContext) {
        this.strings = strings;
        this.mContext = mContext;
    }
//返回页面的个数
    @Override
    public int getGroupCount() {
        int pageCount = strings.length / PAGE_SIZE;
        if (strings.length%PAGE_SIZE!=0)
            pageCount++;
        return pageCount;
    }
//返回页面条目的个数
    @Override
    public int getCount(int i) {
        if (strings.length%PAGE_SIZE!=0)
        {
            if (i == getGroupCount()-1)
                return strings.length%PAGE_SIZE;
        }
        return PAGE_SIZE;
    }
    //返回对应页中对应位置的View
    @Override
    public View getView(int i, int i1, View view) {
        TextView tv;
        if (view == null)
            tv = new TextView(mContext);
        else
            tv = (TextView) view;

        int  index = i * PAGE_SIZE + i1;
        String data = strings[index];
        tv.setText(data);

        //随机大小
        Random random = new Random();
        tv.setTextSize(random.nextInt(7)+18);//18-25
        //随机颜色
        int argb = getRandomColor(random);
        tv.setTextColor(argb);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemOnClickListener != null)
                {
                    onItemOnClickListener.itemOnclickListener(tv.getText().toString());
                }
            }
        });

        return tv;
    }

    private int getRandomColor(Random random) {
        int  alpha = 255;
        int red = random.nextInt(190)+30;//30-220
        int green = random.nextInt(190) + 30;//30-220
        int blue = random.nextInt(190) + 30;//30-220
        return Color.argb(alpha,red,green,blue);
    }
//返回放大或缩小下一组的下标
    //参数一 当前组的下标
    //参数二 true表示放大 false表示缩小
    @Override
    public int getNextGroupOnZoom(int i, boolean b) {
        if (b)
            return (i+1)%getGroupCount();
        else
            return (i - 1 +getGroupCount())%getGroupCount();
    }
    public void registerOnItemOnClickListener(onItemOnClickListener onItemOnClickListener){
        this.onItemOnClickListener = onItemOnClickListener;
    }

    public interface onItemOnClickListener{
         void itemOnclickListener(String item);
    }
}
