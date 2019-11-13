package com.example.p2pfiancial.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

public class RoundProgressOne extends View {

    private Paint mPaint;
    public float num = 0.00f;

    public RoundProgressOne(Context context) {
        this(context, null);
    }

    public RoundProgressOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true); //去毛边



    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE); //空心
        mPaint.setStrokeWidth(18);
        mPaint.setColor(Color.GRAY);
        RectF rectF = new RectF(50, 50, 150, 150);
        canvas.drawArc(rectF, 0, 360, false, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 0, num, false, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(18);
        BigDecimal decimal = new BigDecimal(num/360*100); //省略小数点

        canvas.drawText(decimal.setScale(2,1)+"%", getWidth()/2-30, getHeight()/2, mPaint);
    }

//    public void setRefresh(){
////        num++;
////        invalidate(); //刷新界面
////    }
}
