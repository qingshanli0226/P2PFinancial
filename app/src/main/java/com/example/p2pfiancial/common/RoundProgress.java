package com.example.p2pfiancial.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.p2pfiancial.R;
import com.example.p2pfiancial.util.UIUtils;

public class RoundProgress extends View {

    private Paint mPaint;
    private TypedArray typedArray;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float roundWidth;
    private float textSize;
    private int max;
    private int progress;
    private int width;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义的属性
        //1. 获取TypeArray的对象(调用两个参数的方法)
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);

        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true); //去除毛边

        //2. 取出所有的自定义属性
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWith, UIUtils.dp2px(10));
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize, UIUtils.dp2px(20));
        max = typedArray.getColor(R.styleable.RoundProgress_max, 100);
        progress = typedArray.getColor(R.styleable.RoundProgress_progress, 30);


        //3. 回收处理
        typedArray.recycle();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    //测量: 获取当前视图宽高


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = this.getMeasuredWidth();
    }

    //canvas:画布，对应着视图在布局中的范围区间。范围的左上顶点即为坐标原点
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1. 绘制圆环
        //获取圆心坐标
        int cx = width / 2;
        int cy = width / 2;
        //半径
        float radius = width / 2 - roundWidth / 2;

        mPaint.setColor(roundColor); //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置圆环的样式
        mPaint.setStrokeWidth(roundWidth); //设置圆环的宽度
        canvas.drawCircle(cx, cy, radius, mPaint);


        //2. 绘制圆弧
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        mPaint.setColor(roundProgressColor); //设置画笔颜色
        canvas.drawArc(rectF, 0, progress * 360 / max, false, mPaint);

//3.绘制文本
        String text = progress * 100 / max + "%";
        //设置paint
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(0);
        Rect rect = new Rect();//创建了一个矩形，此时矩形没有具体的宽度和高度
        mPaint.getTextBounds(text, 0, text.length(), rect);//此时的矩形的宽度和高度即为整好包裹文本的矩形的宽高
        //获取左下顶点的坐标
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;


        canvas.drawText(text, x, y, mPaint);
    }
}
