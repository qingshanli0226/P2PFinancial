package com.example.p2pdemo.activity;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.modulebase.BaseChartActivity;
import com.example.p2pdemo.R;
import com.example.p2pdemo.adpter.MyAxisValueFormatter;
import com.example.p2pdemo.adpter.XAxisValueFormatter;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class LineChartActivity  extends BaseChartActivity implements OnChartValueSelectedListener {
    private android.widget.ImageView ivTitleBlack;
    private android.widget.TextView tvTitle;
    private android.widget.ImageView ivTitleSetting;
    private com.github.mikephil.charting.charts.LineChart lineChart;
    private Typeface mTfRegular;
    private Typeface mTfLight;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void setChartData() {
        //填充数据，在这里换成自己的数据源
        List<Entry> valsComp1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            int  a  =  (int)(Math.random()*100);
            valsComp1.add(new Entry(i, a));
        }



        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;


        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(valsComp1);

            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(valsComp1, "New DataSet 1");
            set1.setColor(R.color.my_title_bg);
            set1.setCircleColor(Color.RED);
            set1.setLineWidth(3f);//设置线宽
            set1.setCircleRadius(6f);//设置焦点圆心的大小
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0.0");
            set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
            lineChart.animateXY(2000,2000);
    }}
    @Override
    protected void initChart() {
            Description description = new Description();
            description.setText("学委傻屌事件关注度");
            description.setTextColor(Color.RED);
            description.setTextSize(10);
            lineChart.setDescription(description);//设置图表描述信息
            lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
            lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
            lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
            lineChart.setDrawBorders(false);//禁止绘制图表边框的线


            YAxis axisLeft = lineChart.getAxisLeft();
            //设置坐标轴最大值：如果设置那么轴不会根据传入数据自动设置
            axisLeft.setAxisMaximum(100f);
            //设置坐标轴最小值：如果设置那么轴不会根据传入数据自动设置
            axisLeft.setAxisMinimum(0f);
            //设置标签个数以及是否精确（false为模糊，true为精确）
            axisLeft.setLabelCount(10, false);
            //如果设置为true那么下面方法设置最小间隔生效，默认为false
            axisLeft.setGranularityEnabled(true);
            //设置Y轴的值之间的最小间隔。这可以用来避免价值复制当放大到一个地步，小数设置轴不再数允许区分两轴线之间的值。
            axisLeft.setGranularity(10f);
            //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
            //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
            //lineChart.setLogEnabled(true);//打印日志
            //lineChart.notifyDataSetChanged();//刷新数据
            //lineChart.invalidate();//重绘;

            ArrayList<String> x = new ArrayList<String>();

            x.add("Jan");
            x.add("Feb");
            x.add("Mar");
            x.add("Apr");
            x.add("May");
            x.add("Jun");
            x.add("Jul");
            x.add("Aug");
            x.add("Sep");
            x.add("Okt");
            x.add("Nov");
            x.add("Dec");
            IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter(x);
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(xAxisFormatter);


            IAxisValueFormatter custom = new MyAxisValueFormatter();
            YAxis leftAxis = lineChart.getAxisLeft();
            leftAxis.setLabelCount(8, false);
            leftAxis.setValueFormatter(custom);
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            leftAxis.setSpaceTop(15f);
            leftAxis.setAxisMinimum(0f);

            lineChart.getAxisRight().setEnabled(false);

        }



    @Override
    protected void initTitle() {
        ivTitleBlack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
        tvTitle.setText("投资管理");
        ivTitleBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_linechart;
    }

    protected void initView() {
//        mTfRegular = Typeface.createFromAsset(getAssets(), "com/example/p2pdemo/assets/OpenSans-Regular.ttf");
//        mTfLight = Typeface.createFromAsset(getAssets(), "com/example/p2pdemo/assets/OpenSans-Light.ttf");

        ivTitleBlack = findViewById(R.id.iv_title_black);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleSetting = findViewById(R.id.iv_title_setting);
        lineChart = findViewById(R.id.mLineChart);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
