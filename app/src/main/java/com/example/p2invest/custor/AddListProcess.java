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

public class AddListProcess extends View {
    private int roundColor;//圆环的颜色
    private int roundProgressColor ;//圆弧的颜色
    private int textColor;//文本的颜色

    private float roundWidth ;
    private float textSize ;

    private int max;
    private int width;
    private int maxProcess;

    public int getMaxProcess() {
        return maxProcess;
    }

    public void setMaxProcess(int maxProcess) {
        this.maxProcess = maxProcess;
        postInvalidate();
    }

    private Paint paint;
    public AddListProcess(Context context) {
        this(context,null);
    }
    public AddListProcess(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddListProcess(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        roundColor = Color.parseColor("#AAAAAA");
        roundProgressColor = Color.BLUE;
        textColor = Color.BLUE;
        roundWidth = 5f;
        textSize =30;
        max = 100;

    }

    //测量：获取当前视图宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //1.绘制圆环
        //获取圆心坐标
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        float radius=getWidth()/2-roundWidth/2;
        paint.setColor(roundColor);//设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//设置圆环的样式
        paint.setStrokeWidth(roundWidth);//设置圆环的宽度
        canvas.drawCircle(cx, cy, radius, paint);

        //2.绘制圆弧
        RectF rectF = new RectF(roundWidth/2, roundWidth/2, getWidth()-roundWidth/2, getWidth()-roundWidth/2);
        paint.setColor(roundProgressColor);//设置画笔颜色
        canvas.drawArc(rectF,0,maxProcess * 360 / max ,false,paint);
        Log.i("progress", "progress: "+maxProcess);

        //3.绘制文本
        String text = maxProcess * 100 / max  + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        Rect rect = new Rect();//创建了一个矩形，此时矩形没有具体的宽度和高度
        paint.getTextBounds(text,0,text.length(),rect);//此时的矩形的宽度和高度即为整好包裹文本的矩形的宽高
        //获取左下顶点的坐标
        int x = getWidth() / 2-20 ;
        int y = getHeight() / 2+20;


        canvas.drawText(text,x,y,paint);
    }

}
