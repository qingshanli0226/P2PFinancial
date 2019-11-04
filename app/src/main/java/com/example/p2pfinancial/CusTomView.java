package com.example.p2pfinancial;

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
    int sweepAngle = 0;
    int txt = 0;

    public CusTomView(Context context) {
        super(context);
        init();
    }

    public CusTomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        mHeight = h;
//        mWidth = w;
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
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setColor(Color.WHITE);
        canvas.translate(mWidth / 2, 180);
        RectF rectF = new RectF(-150, -150, 150, 150);
        canvas.drawArc(rectF, 0, 360, true, paint);
        paint.setColor(getResources().getColor(R.color.color_one));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        canvas.drawArc(rectF, 0, 360, false, paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF, 0, sweepAngle, false, paint);
        paint.setColor(getResources().getColor(R.color.colorblue));
        paint.setStrokeWidth(3);
        paint.setTextSize(30);
        canvas.drawText(txt + " %", 0, 0, paint);
        count();
    }

    public void count() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (sweepAngle <= 360 * 0.9) {
                    if (txt < 90) {
                        txt += 5;
                    } else {
                        txt = 45;
                    }
                    sweepAngle++;
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        invalidate();
    }
}
