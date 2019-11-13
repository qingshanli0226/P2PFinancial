package com.example.month6.view.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.month6.R;

public class CustomProgressView extends View {
    int pad;
    private int num=0;
    int allcolor;
    int progresscolor;
    int textcolor;
    float textsize;
    float circlesize;
    public CustomProgressView(Context context) {
        this(context,null);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressView);
        allcolor = typedArray.getColor(R.styleable.CustomProgressView_all_color, Color.GRAY);
        progresscolor = typedArray.getColor(R.styleable.CustomProgressView_progress_color, Color.BLUE);
        textcolor = typedArray.getColor(R.styleable.CustomProgressView_text_color, Color.BLUE);
        textsize = typedArray.getDimension(R.styleable.CustomProgressView_text_size, 20f);
        circlesize = typedArray.getDimension(R.styleable.CustomProgressView_circle_size, 28f);
        pad= (int) (circlesize/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //空心  抗锯齿
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(allcolor);
        paint.setStrokeWidth(circlesize);
        //
        RectF rectF = new RectF(pad,pad,getWidth()-pad,getHeight()-pad);
        canvas.drawArc(rectF,0,360,false,paint);
        //进度
        paint.setColor(progresscolor);
        canvas.drawArc(rectF,0,num,false,paint);
        //文字进度
        paint.setColor(textcolor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setTextSize(textsize);
        canvas.drawText(((num*100)/360)+"%", (getWidth()/2)-pad,(getHeight()/2)+pad,paint);
    }

    public void setNum(int num) {
        this.num = num;
        postInvalidate();
    }

    public void reFush(double count){
        do {
            num++;
            postInvalidate();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (num <= (360 * count));
    }
}
