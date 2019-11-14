package com.example.month6.view.activirys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.common.diyviews.baseclass.BaseActivity
import com.example.month6.R
import com.github.mikephil.charting.data.Entry
import kotlinx.android.synthetic.main.activity_line_chart.*

class LineChartActivity : BaseActivity() {
    override fun initData() {
        var list:ArrayList<Entry> = ArrayList()
        list.add(Entry(5f,50f))
        list.add(Entry(10f,66f))
        list.add(Entry(15f,120f))
        list.add(Entry(20f,30f))
        list.add(Entry(25f,110f))
        list.add(Entry(30f,50f))
        list.add(Entry(50f,180f))
        
    }

    override fun initView() {
        lineChart.isDragEnabled=true
        lineChart.description.isEnabled=false
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_line_chart
    }


}
