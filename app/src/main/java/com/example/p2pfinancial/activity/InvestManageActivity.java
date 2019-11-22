package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;

import com.example.base.BaseActivity;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//投资管理页面
public class InvestManageActivity extends BaseActivity {

    private LineChart mLineChart;
    private TitleBar mTitle;

    //设置布局
    @Override
    protected int setLayout() {
        return R.layout.activity_invest_manage;
    }

    //初始化
    @Override
    protected void initView() {
        mLineChart = findViewById(R.id.mLineChart);
        mTitle = findViewById(R.id.title_invest_manage);
    }

    @Override
    public void initData() {
        super.initData();
        mTitle.setTitleText("折线图Demo");
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

        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(5, 50));
        values.add(new Entry(15, 66));
        values.add(new Entry(25, 120));
        values.add(new Entry(35, 30));
        values.add(new Entry(45, 10));
        values.add(new Entry(50, 110));
        values.add(new Entry(65, 30));
        values.add(new Entry(75, 160));
        values.add(new Entry(95, 130));
        values.add(new Entry(105, 100));
        values.add(new Entry(115, 190));

        setData(values);
        List<ILineDataSet> dataSets = mLineChart.getData().getDataSets();
        for (ILineDataSet dataSet : dataSets) {
            LineDataSet set = (LineDataSet) dataSet;
            set.setDrawFilled(false);
        }
        mLineChart.invalidate();
        Legend legend = mLineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);

    }

    LineDataSet lineDataSet;

    private void setData(ArrayList<Entry> values) {
        if (mLineChart.getData() != null && mLineChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "年度总结报告");
            lineDataSet.enableDashedLine(10f, 5f, 0f);
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
            lineDataSet.setColor(Color.BLACK);
            lineDataSet.setCircleColor(Color.BLACK);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(3f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(9f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFormLineWidth(1f);
            lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet.setFormSize(15.f);
//            lineDataSet.setFillColor(Color.YELLOW);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);

            mLineChart.setData(data);

        }
    }
}
