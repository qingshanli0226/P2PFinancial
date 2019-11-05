package com.example.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CusTomView extends View {

    int mWidth;
    int mHeight;
    float sweepAngle = 0;
    int txt = 0;
    float count = 0;

    public CusTomView(Context context) {
        super(context);
    }

    public CusTomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = this.getMeasuredWidth();
        mHeight = this.getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerWidth = mWidth / 2;
        int centerHeight = mHeight / 2;
        int radius = 170;
        int strokeWidth = 25;
        String drawText = txt + "%";

        Paint paint = new Paint();
        float textX = mWidth / 2 - paint.measureText(drawText) / 2;
        float textY = mHeight / 2 - paint.measureText(drawText) / 2;
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerWidth, radius, radius, paint);
        RectF rectF = new RectF(centerWidth - radius, strokeWidth / 2, centerWidth + radius, radius * 2 + strokeWidth / 2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.color_one));
        paint.setStrokeWidth(strokeWidth);
        canvas.drawArc(rectF, 0, 360, false, paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF, 0, sweepAngle, false, paint);
        paint.setTextSize(50);
        paint.setColor(getResources().getColor(R.color.colorblue));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        canvas.drawText(drawText, textX - 53 / 2, radius + 53 / 2, paint);
        try {
            Thread.sleep(1000/36);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count();    
    }

    public void count() {
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
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
        invalidate();
    }
}
