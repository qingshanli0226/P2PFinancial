package com.example.p2pdemo.adpter;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.p2pdemo.R;

import java.util.List;
import java.util.Random;

public class InvestHotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public InvestHotAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //随机颜色
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(ranColor);
        drawable.setStroke(1,ranColor);
        drawable.setCornerRadius(10);
        TextView text = helper.getView(R.id.investHot_item_text);
        text.setBackground(drawable);
        text.setText(item);
    }
}
