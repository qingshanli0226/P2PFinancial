package com.bwei.p2p;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundProgress extends View {
    //使用自定义属性来初始化如下的变量
    private int roundColor;//圆环的颜色
    private int roundProgressColor ;//圆弧的颜色
    private int textColor;//文本的颜色

    private float roundWidth ;//圆环的宽度
    private float textSize ;//文本的字体大小

    private int max;//圆环的最大值
    private int progress;//圆环的进度

    private int width;//当前视图的宽度（=高度）

    private Paint paint;//画笔

    public RoundProgress(Context context) {
        this(context,null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint= new Paint();
        paint.setAntiAlias(true);
        roundColor= Color.parseColor("#5A6E9E");
        roundProgressColor=Color.RED;
        textColor=Color.GRAY;
        textSize=20f;
        roundWidth=10f;
        max=100;
        progress=0;

    }
    public void setProgress() {
       if (progress<=85){
           progress ++;
           postInvalidate();//主线程、分线程都可以如此调用
       }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=this.getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cy=width/2;
        float radius =width/2-roundWidth/2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cy,cy,radius,paint);
        //2.绘制圆弧
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);//设置画笔颜色
        canvas.drawArc(rectF,0,progress * 360 / max ,false,paint);

        //3.绘制文本
        String text = progress * 100 / max  + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        //获取左下顶点的坐标
        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);//此时的矩形的宽度和高度即为整好包裹文本的矩形的宽高
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;
        canvas.drawText(text,x,y,paint);

    }
}
