package com.example.p2invest.custor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyProgress extends View {
    private int roundColor;//圆环的颜色
    private int roundProgressColor ;//圆弧的颜色
    private int textColor;//文本的颜色

    private float roundWidth ;
    private float textSize ;

    private int max;
    private int progress;

    private int width;

    private Paint paint;
    public MyProgress(Context context) {
        this(context,null);
    }
    public MyProgress(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        roundColor = Color.parseColor("#5A6E9E");
        roundProgressColor = Color.RED;
        textColor = Color.GRAY;
        roundWidth = 10f;
        textSize =20f;
        max = 100;
        progress = 0;

    }
    public int getProgress() {
        return progress;
    }

    public void setProgress() {
       if (progress<85){
           progress++;
           postInvalidate();

           Log.i("setProgress", "setProgress: "+progress);
       }
    }

    //测量：获取当前视图宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
        Log.i("width", "width: "+width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //1.绘制圆环
        //获取圆心坐标
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        paint.setColor(roundColor);//设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//设置圆环的样式
        paint.setStrokeWidth(20f);//设置圆环的宽度
        canvas.drawCircle(cx, cy, getHeight()/2-30, paint);

        //2.绘制圆弧
        RectF rectF = new RectF(getWidth()/4+3, 30, getWidth() / 2+getWidth()/4-3, getHeight()-30);
        paint.setColor(roundProgressColor);//设置画笔颜色
        canvas.drawArc(rectF,0,progress * 360 / max ,false,paint);
        Log.i("progress", "progress: "+progress);

        //3.绘制文本
        String text = progress * 100 / max  + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(50);
        paint.setStrokeWidth(0);
        Rect rect = new Rect();//创建了一个矩形，此时矩形没有具体的宽度和高度
        paint.getTextBounds(text,0,text.length(),rect);//此时的矩形的宽度和高度即为整好包裹文本的矩形的宽高
        //获取左下顶点的坐标
        int x = getWidth() / 2-20 ;
        int y = getHeight() / 2+20;


        canvas.drawText(text,x,y,paint);
    }

}
