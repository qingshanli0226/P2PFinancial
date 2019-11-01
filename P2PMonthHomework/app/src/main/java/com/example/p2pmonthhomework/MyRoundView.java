package com.example.p2pmonthhomework;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyRoundView extends View {

    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float roundWidth;
    private float textSize;

    private int max;
    private int progress;

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = width / 2;
        int cy = width / 2;
        float radius = width / 2 - roundWidth / 2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx, cy, radius, paint);

        //2.绘制圆弧
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);//设置画笔颜色
        canvas.drawArc(rectF, 0, progress * 360 / max, false, paint);

        //3.绘制文本
        String text = progress * 100 / max + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        Rect rect = new Rect();//创建了一个矩形，此时矩形没有具体的宽度和高度
        paint.getTextBounds(text, 0, text.length(), rect);//此时的矩形的宽度和高度即为整好包裹文本的矩形的宽高
        //获取左下顶点的坐标
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;


        canvas.drawText(text, x, y, paint);
    }
}
