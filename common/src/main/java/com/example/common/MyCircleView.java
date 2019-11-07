package com.example.common;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCircleView extends View {


    int width;
    int height;
    float sweepAngle = 0;
    int txt = 0;
    float count = 0;



    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);



    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        int cx=width/2;
//        int cy=height/2;
//        float radius=170;
//        int strokeWidth=25;
//        canvas.drawCircle(cx,cy,radius,paint);
//
//        RectF rectF = new RectF(cx - radius, strokeWidth / 2, cx + radius, radius - 2 + strokeWidth / 2);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(rectF,0,sweep,true,paint);
//
//        String drawText=count+"%";
//
//        float tx = width / 2 - paint.measureText(drawText) / 2;
//        float ty = height / 2 - paint.measureText(drawText) / 2;
//
//
////        canvas.drawText(drawText,tx-53/2,ty+53/2,paintText);


        int centerWidth = width / 2;
        int centerHeight = height / 2;
        int radius = 170;
        int strokeWidth = 25;
        String drawText = txt + "%";

        Paint paint = new Paint();
        float textX = width / 2 - paint.measureText(drawText) / 2;
        float textY = height / 2 - paint.measureText(drawText) / 2;
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerWidth, radius, radius, paint);
        RectF rectF = new RectF(centerWidth - radius, strokeWidth / 2, centerWidth + radius, radius * 2 + strokeWidth / 2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawArc(rectF, 0, 360, false, paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF, 0, sweepAngle, false, paint);
        paint.setTextSize(50);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        canvas.drawText(drawText, textX - 53 / 2, radius + 53 / 2, paint);
        try {
            Thread.sleep(1000 / 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        circleThred();


    }

    private void circleThred() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (sweepAngle <= 360 * 0.9) {
                    if (txt < 90) {
                        txt += 10;
                    }
                    count += 0.1;
                    sweepAngle = 360 * count;
                }

            }
        }).start();
        invalidate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();


    }







}
