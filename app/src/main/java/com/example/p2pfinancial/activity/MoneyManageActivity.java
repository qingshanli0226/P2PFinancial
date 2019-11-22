package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.base.BaseActivity;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

//资产管理页面
public class MoneyManageActivity extends BaseActivity {

    TitleBar mTitle;
    PieChart mPieChart;

    //设置布局
    @Override
    protected int setLayout() {
        return R.layout.activity_money_manage;
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.title_money);
        mPieChart = findViewById(R.id.mPieChart);
    }

    @Override
    public void initData() {
        super.initData();

        mTitle.setTitleText("饼状图Demo");
        mTitle.setLeftText("<");
        mTitle.setLeftTextColor(getResources().getColor(R.color.colorWhite));
        mTitle.setTitleInterface(new TitleBar.TitleInterface() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });


        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);

        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);


        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(34, "华为"));
        entries.add(new PieEntry(15, "oppo"));
        entries.add(new PieEntry(16, "vivo"));
        entries.add(new PieEntry(36, "三星"));

        setData(entries);
        mPieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);

    }

    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "Android厂商2019年手机占有率");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);


        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
//        for (IDataSet<?> set : mPieChart.getData().getDataSets()) {
//            set.setDrawValues(!set.isDrawValuesEnabled());
//        }
        mPieChart.invalidate();
    }
}
