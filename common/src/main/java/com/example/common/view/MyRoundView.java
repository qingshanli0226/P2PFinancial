package com.example.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.common.R;

public class MyRoundView extends View implements Runnable {

    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float roundWidth;
    private float textSize;

    private int max;
    private int progress;
    private int progress2;

    private int width;

    private Paint paint;

    public MyRoundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRoundView);

        roundColor = typedArray.getColor(R.styleable.MyRoundView_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.MyRoundView_roundProgressColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.MyRoundView_textColor, Color.GREEN);
        roundWidth = typedArray.getDimension(R.styleable.MyRoundView_roundWith, 10);
        textSize = typedArray.getDimension(R.styleable.MyRoundView_textSize, 20);
        max = typedArray.getInteger(R.styleable.MyRoundView_max, 100);
        progress = typedArray.getInteger(R.styleable.MyRoundView_progress, 30);
        progress2 = progress;

        typedArray.recycle();

        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = width / 2;
        int cy = width / 2;
        float radius = width / 2 - roundWidth / 2;
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx, cy, radius, paint);

        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);
        canvas.drawArc(rectF, 0, progress2 * 360 / max, false, paint);
        String text = progress2 * 100 / max + "%";
        paint.setColor(textColor);
        paint.setStrokeWidth(0);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);

        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;


        canvas.drawText(text, x, y, paint);
    }

    @Override
    public void run() {
        progress2 = 0;
        while (progress2 < progress) {
            try {
                Thread.sleep(1000 / 36);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress2++;
            postInvalidate();
        }
    }
}
