package com.example.month6.view.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomProgressView extends View {
    int pad=30;
    public int num=0;
    public CustomProgressView(Context context) {
        super(context);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //空心  抗锯齿
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(28);
        RectF rectF = new RectF(pad,pad,getWidth()-pad,getHeight()-pad);
        canvas.drawArc(rectF,0,360,false,paint);
        //进度
        paint.setColor(Color.RED);
        canvas.drawArc(rectF,0,num,false,paint);
        //文字进度
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setTextSize(60);
        canvas.drawText(((num*100)/360)+"%",(getWidth()/2)-pad-10,(getHeight()/2)+pad,paint);
    }
    public void reFush(){
        num++;
        invalidate();
    }
}
