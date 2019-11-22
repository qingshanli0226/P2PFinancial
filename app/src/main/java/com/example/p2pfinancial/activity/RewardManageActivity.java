package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.base.BaseActivity;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

//投资管理(直观)页面
public class RewardManageActivity extends BaseActivity {

    TitleBar mTitle;
    BarChart mBarChart;
    @Override
    protected int setLayout() {
        return R.layout.activity_reward_manage;
    }

    @Override
    protected void initView() {
        mTitle=findViewById(R.id.title_reward);
        mBarChart=findViewById(R.id.mBarChart);
    }

    @Override
    public void initData() {
        super.initData();

        mTitle.setTitleText("柱状图Demo");
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


        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(5, 50));
        values.add(new BarEntry(15, 66));
        values.add(new BarEntry(25, 120));
        values.add(new BarEntry(35, 30));
        values.add(new BarEntry(45, 10));
        values.add(new BarEntry(50, 110));
        values.add(new BarEntry(65, 30));
        values.add(new BarEntry(75, 160));
        values.add(new BarEntry(95, 130));
        values.add(new BarEntry(105, 100));
        values.add(new BarEntry(115, 190));


        mBarChart.setPinchZoom(false);

        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        xAxis.setGranularity(1f);
        xAxis.setLabelCount(11);
        xAxis.setAxisMaximum(120f);
        xAxis.setAxisMinimum(0f);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(200f);
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(200f);



        setData(values);
    }

    private void setData(ArrayList<BarEntry> yVals1) {
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "2017年工资涨幅");
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mBarChart.setData(data);
        }
    }
}
