package com.example.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    private int mWihth;
    private int mHeight;
    private String sweepAngle;
    private int cutterNum;
    private int cutterText;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWihth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(15);
        paint.setColor(getResources().getColor(R.color.colorGray));

        RectF rectF = new RectF(15, 15, mWihth - 15, mHeight - 15);
        canvas.drawArc(rectF, 0, 360, false, paint);
        paint.setColor(getResources().getColor(R.color.colorBlue));
        canvas.drawArc(rectF, 0, cutterNum, false, paint);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        canvas.drawText(cutterText + "%", mWihth / 2 - paint.measureText(sweepAngle + "%") / 2+15 , mHeight / 2 + paint.measureText(sweepAngle + "%") / 2-15   , paint);
        yuan();
    }

    public void setSweepAngle(String sweepAngle) {
        this.sweepAngle = sweepAngle;
        cutterText = Integer.parseInt(sweepAngle);
    }


    public void yuan() {
        if (cutterNum <= (360 * Float.parseFloat("0." + sweepAngle))) {
            cutterNum++;
            try {
                Thread.sleep(1000 / 144);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        invalidate();
    }
}
