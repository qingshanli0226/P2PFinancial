package com.example.p2pdemo.adpter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.util.ArrayList;


public class XAxisValueFormatter  implements IAxisValueFormatter{
    private ArrayList<String> list;

    public XAxisValueFormatter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int postion = (int) value;
        if (postion>= list.size()-1)
            postion = 0;

        return list.get(postion);
    }
}
